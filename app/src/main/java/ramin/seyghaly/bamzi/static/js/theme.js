/**
 * بامزی (Bamzi) - مدیریت هوشمند تم تاریک و روشن (Dark / Light Theme)
 */

(function () {
    const THEME_STORAGE_KEY = 'bamzi_theme_preference';

    // بازیابی تم ذخیره‌شده یا ترجیح سیستم‌عامل
    function getPreferredTheme() {
        const savedTheme = localStorage.getItem(THEME_STORAGE_KEY);
        if (savedTheme) {
            return savedTheme;
        }
        return window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches
            ? 'dark'
            : 'light';
    }

    // اعمال تم بر روی تگ html
    function applyTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);
        localStorage.setItem(THEME_STORAGE_KEY, theme);
        updateToggleButtons(theme);

        // ارسال اونت برای کامپوننت‌های دیگر
        window.dispatchEvent(new CustomEvent('bamziThemeChanged', { detail: { theme } }));
    }

    // بروزرسانی آیکون‌های دکمه سوئیچ
    function updateToggleButtons(theme) {
        const toggleButtons = document.querySelectorAll('[data-action="toggle-theme"]');
        toggleButtons.forEach(btn => {
            const iconSun = btn.querySelector('.theme-icon-sun');
            const iconMoon = btn.querySelector('.theme-icon-moon');
            if (iconSun && iconMoon) {
                if (theme === 'dark') {
                    iconSun.style.display = 'block';
                    iconMoon.style.display = 'none';
                    btn.setAttribute('title', 'تغییر به حالت روز');
                    btn.setAttribute('aria-label', 'حالت روز');
                } else {
                    iconSun.style.display = 'none';
                    iconMoon.style.display = 'block';
                    btn.setAttribute('title', 'تغییر به حالت شب');
                    btn.setAttribute('aria-label', 'حالت شب');
                }
            }
        });
    }

    // اعمال تم اولیه فوراً برای جلوگیری از پرش رنگ (Flash of Unstyled Content)
    const initialTheme = getPreferredTheme();
    document.documentElement.setAttribute('data-theme', initialTheme);

    // افزودن Listener بعد از بارگذاری DOM
    document.addEventListener('DOMContentLoaded', () => {
        updateToggleButtons(initialTheme);

        document.addEventListener('click', (e) => {
            const toggleBtn = e.target.closest('[data-action="toggle-theme"]');
            if (toggleBtn) {
                e.preventDefault();
                const currentTheme = document.documentElement.getAttribute('data-theme') || 'light';
                const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
                applyTheme(newTheme);
            }
        });
    });

    // هماهنگی با تغییر ترجیح سیستم‌عامل
    if (window.matchMedia) {
        window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
            if (!localStorage.getItem(THEME_STORAGE_KEY)) {
                applyTheme(e.matches ? 'dark' : 'light');
            }
        });
    }

    // دسترسی جهانی
    window.BamziTheme = {
        get: () => document.documentElement.getAttribute('data-theme') || 'light',
        set: applyTheme,
        toggle: () => {
            const current = document.documentElement.getAttribute('data-theme') || 'light';
            applyTheme(current === 'dark' ? 'light' : 'dark');
        }
    };
})();
