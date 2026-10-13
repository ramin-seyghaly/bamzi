/**
 * بامزی (Bamzi) - تعاملات عمومی فرانت‌اند و مدیریت منو
 */

document.addEventListener('DOMContentLoaded', () => {
    // ۱. مدیریت باز و بسته شدن منوی موبایل
    const mobileMenuBtn = document.getElementById('mobileMenuToggle');
    const mobileNavDrawer = document.getElementById('mobileNavDrawer');

    if (mobileMenuBtn && mobileNavDrawer) {
        mobileMenuBtn.addEventListener('click', (e) => {
            e.stopPropagation();
            const isOpen = mobileNavDrawer.classList.toggle('open');
            mobileMenuBtn.setAttribute('aria-expanded', isOpen);
        });

        // بستن منو با کلیک در هر نقطه دیگر صفحه
        document.addEventListener('click', (e) => {
            if (!mobileNavDrawer.contains(e.target) && !mobileMenuBtn.contains(e.target)) {
                mobileNavDrawer.classList.remove('open');
                mobileMenuBtn.setAttribute('aria-expanded', 'false');
            }
        });

        // بستن منوی موبایل با کلیک روی هر لینک داخلی
        const mobileLinks = mobileNavDrawer.querySelectorAll('.nav-link');
        mobileLinks.forEach(link => {
            link.addEventListener('click', () => {
                mobileNavDrawer.classList.remove('open');
                mobileMenuBtn.setAttribute('aria-expanded', 'false');
            });
        });
    }

    // ۲. اسکرول نرم برای لینک‌های درون‌صفحه‌ای (Smooth Scroll)
    const anchorLinks = document.querySelectorAll('a[href^="#"]');
    anchorLinks.forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            const targetId = this.getAttribute('href');
            if (targetId === '#' || targetId === '') return;

            const targetElement = document.querySelector(targetId);
            if (targetElement) {
                e.preventDefault();
                const headerOffset = 90;
                const elementPosition = targetElement.getBoundingClientRect().top;
                const offsetPosition = elementPosition + window.pageYOffset - headerOffset;

                window.scrollTo({
                    top: offsetPosition,
                    behavior: 'smooth'
                });

                // بروزرسانی کلاس active در لینک‌ها
                document.querySelectorAll('.nav-link').forEach(l => l.classList.remove('active'));
                this.classList.add('active');
            }
        });
    });

    // ۳. مدیریت تعامل فرم خبرنامه فوتر
    const newsletterForm = document.getElementById('footerNewsletterForm');
    if (newsletterForm) {
        newsletterForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const input = newsletterForm.querySelector('.newsletter-input');
            const btn = newsletterForm.querySelector('button');
            if (input && input.value.trim()) {
                const originalText = btn.innerHTML;
                btn.innerHTML = 'ثبت شد ✨';
                btn.classList.add('clay-btn-secondary');
                btn.classList.remove('clay-btn-primary');
                input.value = '';
                input.placeholder = 'با تشکر از عضویت شما!';
                setTimeout(() => {
                    btn.innerHTML = originalText;
                    btn.classList.remove('clay-btn-secondary');
                    btn.classList.add('clay-btn-primary');
                    input.placeholder = 'ایمیل خود را وارد کنید...';
                }, 3000);
            }
        });
    }

    // ۴. مدیریت مودال‌های ورود و ثبت‌نام
    const loginModal = document.getElementById('loginModalBackdrop');
    const registerModal = document.getElementById('registerModalBackdrop');

    function openModal(modal) {
        if (!modal) return;
        modal.classList.add('active');
        document.body.style.overflow = 'hidden';
    }

    function closeModal(modal) {
        if (!modal) return;
        modal.classList.remove('active');
        document.body.style.overflow = '';
    }

    function closeAllModals() {
        closeModal(loginModal);
        closeModal(registerModal);
    }

    // باز کردن مودال ورود با کلیک روی دکمه‌های ورود
    document.querySelectorAll('[href="#login-modal"], #navLoginBtn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            closeModal(registerModal);
            openModal(loginModal);
        });
    });

    // باز کردن مودال ثبت‌نام با کلیک روی دکمه‌های ثبت‌نام
    document.querySelectorAll('[href="#register-modal"], #navRegisterBtn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            closeModal(loginModal);
            openModal(registerModal);
        });
    });

    // دکمه‌های بستن مودال
    document.querySelectorAll('[data-close-modal]').forEach(closeBtn => {
        closeBtn.addEventListener('click', (e) => {
            e.preventDefault();
            const targetId = closeBtn.getAttribute('data-close-modal');
            const targetModal = document.getElementById(targetId);
            closeModal(targetModal);
        });
    });

    // بستن با کلیک روی پس‌زمینه تیره
    [loginModal, registerModal].forEach(modal => {
        if (modal) {
            modal.addEventListener('click', (e) => {
                if (e.target === modal) {
                    closeModal(modal);
                }
            });
        }
    });

    // سوییچ بین مودال ورود و ثبت‌نام
    const switchToRegister = document.getElementById('switchToRegister');
    if (switchToRegister) {
        switchToRegister.addEventListener('click', (e) => {
            e.preventDefault();
            closeModal(loginModal);
            openModal(registerModal);
        });
    }

    const switchToLogin = document.getElementById('switchToLogin');
    if (switchToLogin) {
        switchToLogin.addEventListener('click', (e) => {
            e.preventDefault();
            closeModal(registerModal);
            openModal(loginModal);
        });
    }

    // ۵. مدیریت مودال اتاق بازی آنلاین (Room Modal)
    const roomModal = document.getElementById('roomModalBackdrop');
    const tabCreateRoom = document.getElementById('tabCreateRoom');
    const tabJoinRoom = document.getElementById('tabJoinRoom');
    const createRoomForm = document.getElementById('createRoomForm');
    const joinRoomForm = document.getElementById('joinRoomForm');
    const roomGameSelect = document.getElementById('roomGameSelect');

    // باز کردن مودال اتاق با کلیک روی دکمه‌های «شروع بازی آنلاین»
    document.querySelectorAll('[data-action="open-room-modal"]').forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            const gameCode = btn.getAttribute('data-game-code');
            if (roomGameSelect && gameCode) {
                roomGameSelect.value = gameCode;
            }
            openModal(roomModal);
        });
    });

    if (tabCreateRoom && tabJoinRoom) {
        tabCreateRoom.addEventListener('click', () => {
            tabCreateRoom.classList.add('clay-btn-primary');
            tabCreateRoom.classList.remove('clay-btn-secondary');
            tabJoinRoom.classList.add('clay-btn-secondary');
            tabJoinRoom.classList.remove('clay-btn-primary');
            createRoomForm.style.display = 'block';
            joinRoomForm.style.display = 'none';
        });

        tabJoinRoom.addEventListener('click', () => {
            tabJoinRoom.classList.add('clay-btn-primary');
            tabJoinRoom.classList.remove('clay-btn-secondary');
            tabCreateRoom.classList.add('clay-btn-secondary');
            tabCreateRoom.classList.remove('clay-btn-primary');
            createRoomForm.style.display = 'none';
            joinRoomForm.style.display = 'block';
        });
    }

    const btnSubmitJoinRoom = document.getElementById('btnSubmitJoinRoom');
    const joinRoomCodeInput = document.getElementById('joinRoomCode');
    if (btnSubmitJoinRoom && joinRoomCodeInput) {
        btnSubmitJoinRoom.addEventListener('click', () => {
            const code = joinRoomCodeInput.value.trim().toUpperCase();
            if (!code) {
                alert('لطفاً کد ۶ رقمی اتاق را وارد کنید.');
                return;
            }
            window.location.href = `/api/games/rooms/${code}/`;
        });
    }

    // بستن مودال با کلیک روی پس‌زمینه
    if (roomModal) {
        roomModal.addEventListener('click', (e) => {
            if (e.target === roomModal) {
                closeModal(roomModal);
            }
        });
    }

    // ۶. مدیریت تعاملی جعبه‌ابزار گیمرها (Gamer Tools)
    const toolsModal = document.getElementById('toolsModalBackdrop');
    const toolsModalTitle = document.getElementById('toolsModalTitle');
    const toolsModalSubtitle = document.getElementById('toolsModalSubtitle');
    const toolWidgets = {
        dice: document.getElementById('toolWidgetDice'),
        coin: document.getElementById('toolWidgetCoin'),
        timer: document.getElementById('toolWidgetTimer'),
        scoreboard: document.getElementById('toolWidgetScoreboard')
    };

    const toolMeta = {
        dice: { title: 'تاس مجازی سه‌بعدی', subtitle: 'پرتاب تاس رندوم و عادلانه برای بازی‌های تخته‌ای' },
        coin: { title: 'شیر یا خط دیجیتال', subtitle: 'تصمیم‌گیری سریع و تعیین نوبت شروع بازی' },
        timer: { title: 'تایمر و ساعت مسابقه', subtitle: 'زمان‌سنج حرفه‌ای نوبت بازی با زنگ پایان' },
        scoreboard: { title: 'اسکوربورد و ثبت امتیاز', subtitle: 'شمارنده ساده و سریع دست‌ها و گل‌های مسابقه' }
    };

    // باز کردن ویجت ابزار انتخابی
    document.querySelectorAll('[data-action="open-tool-modal"]').forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            const toolType = btn.getAttribute('data-tool');
            if (!toolType || !toolWidgets[toolType]) return;

            // پنهان کردن بقیه ویجت‌ها
            Object.values(toolWidgets).forEach(w => { if (w) w.style.display = 'none'; });
            toolWidgets[toolType].style.display = 'flex';

            if (toolMeta[toolType]) {
                toolsModalTitle.textContent = toolMeta[toolType].title;
                toolsModalSubtitle.textContent = toolMeta[toolType].subtitle;
            }

            openModal(toolsModal);
        });
    });

    // لاجیک تاس ۳بعدی
    const diceCube = document.getElementById('diceCube');
    const btnRollDice = document.getElementById('btnRollDice');
    const diceResultText = document.getElementById('diceResultText');
    const diceDotsMap = {
        1: [5],
        2: [1, 9],
        3: [1, 5, 9],
        4: [1, 3, 7, 9],
        5: [1, 3, 5, 7, 9],
        6: [1, 3, 4, 6, 7, 9]
    };

    function setDiceNumber(num) {
        for (let i = 1; i <= 9; i++) {
            const dot = document.getElementById(`dot${i}`);
            if (dot) dot.classList.remove('active');
        }
        (diceDotsMap[num] || []).forEach(dotId => {
            const dot = document.getElementById(`dot${dotId}`);
            if (dot) dot.classList.add('active');
        });
    }

    if (btnRollDice && diceCube) {
        setDiceNumber(5); // حالت اولیه
        btnRollDice.addEventListener('click', () => {
            diceCube.classList.add('rolling');
            btnRollDice.disabled = true;
            diceResultText.textContent = 'در حال پرتاب تاس...';

            setTimeout(() => {
                const randomNum = Math.floor(Math.random() * 6) + 1;
                setDiceNumber(randomNum);
                diceCube.classList.remove('rolling');
                diceResultText.textContent = `عدد ${randomNum} آمد! 🎲`;
                btnRollDice.disabled = false;
            }, 600);
        });
    }

    // لاجیک شیر یا خط
    const coinDisc = document.getElementById('coinDisc');
    const btnFlipCoin = document.getElementById('btnFlipCoin');
    const coinText = document.getElementById('coinText');
    const coinResultText = document.getElementById('coinResultText');

    if (btnFlipCoin && coinDisc) {
        btnFlipCoin.addEventListener('click', () => {
            coinDisc.classList.add('flipping');
            btnFlipCoin.disabled = true;
            coinResultText.textContent = 'سکه در حال چرخش...';

            setTimeout(() => {
                const isHeads = Math.random() < 0.5;
                coinText.textContent = isHeads ? 'شیر' : 'خط';
                coinResultText.textContent = isHeads ? '«شیر» آمد! 🦁' : '«خط» آمد! 🪙';
                coinDisc.classList.remove('flipping');
                btnFlipCoin.disabled = false;
            }, 750);
        });
    }

    // لاجیک تایمر
    const timerDisplay = document.getElementById('timerDisplay');
    const btnTimerStart = document.getElementById('btnTimerStart');
    const btnTimerReset = document.getElementById('btnTimerReset');
    const timerPresetBtns = document.querySelectorAll('.timer-preset-btn');
    let timerTotalSeconds = 180;
    let timerRemainingSeconds = 180;
    let timerInterval = null;

    function formatTime(secs) {
        const m = Math.floor(secs / 60).toString().padStart(2, '0');
        const s = (secs % 60).toString().padStart(2, '0');
        return `${m}:${s}`;
    }

    function updateTimerDisplay() {
        if (timerDisplay) timerDisplay.textContent = formatTime(timerRemainingSeconds);
    }

    timerPresetBtns.forEach(pBtn => {
        pBtn.addEventListener('click', () => {
            if (timerInterval) {
                clearInterval(timerInterval);
                timerInterval = null;
                btnTimerStart.textContent = 'شروع تایمر ⏱️';
            }
            timerPresetBtns.forEach(b => b.classList.remove('active'));
            pBtn.classList.add('active');
            timerTotalSeconds = parseInt(pBtn.getAttribute('data-time'), 10);
            timerRemainingSeconds = timerTotalSeconds;
            updateTimerDisplay();
        });
    });

    if (btnTimerStart) {
        btnTimerStart.addEventListener('click', () => {
            if (timerInterval) {
                // Pause
                clearInterval(timerInterval);
                timerInterval = null;
                btnTimerStart.textContent = 'ادامه تایمر ▶';
            } else {
                // Start
                if (timerRemainingSeconds <= 0) timerRemainingSeconds = timerTotalSeconds;
                btnTimerStart.textContent = 'توقف ⏸';
                timerInterval = setInterval(() => {
                    timerRemainingSeconds--;
                    updateTimerDisplay();
                    if (timerRemainingSeconds <= 0) {
                        clearInterval(timerInterval);
                        timerInterval = null;
                        btnTimerStart.textContent = 'پایان زمان! 🔔';
                        if (timerDisplay) {
                            timerDisplay.style.color = 'var(--color-danger)';
                            setTimeout(() => timerDisplay.style.color = '', 3000);
                        }
                    }
                }, 1000);
            }
        });
    }

    if (btnTimerReset) {
        btnTimerReset.addEventListener('click', () => {
            if (timerInterval) {
                clearInterval(timerInterval);
                timerInterval = null;
            }
            timerRemainingSeconds = timerTotalSeconds;
            updateTimerDisplay();
            if (btnTimerStart) btnTimerStart.textContent = 'شروع تایمر ⏱️';
        });
    }

    // لاجیک اسکوربورد
    let p1Score = 0;
    let p2Score = 0;
    const scoreP1El = document.getElementById('scoreP1');
    const scoreP2El = document.getElementById('scoreP2');

    document.getElementById('btnP1Plus')?.addEventListener('click', () => { p1Score++; if (scoreP1El) scoreP1El.textContent = p1Score; });
    document.getElementById('btnP1Minus')?.addEventListener('click', () => { if (p1Score > 0) p1Score--; if (scoreP1El) scoreP1El.textContent = p1Score; });
    document.getElementById('btnP2Plus')?.addEventListener('click', () => { p2Score++; if (scoreP2El) scoreP2El.textContent = p2Score; });
    document.getElementById('btnP2Minus')?.addEventListener('click', () => { if (p2Score > 0) p2Score--; if (scoreP2El) scoreP2El.textContent = p2Score; });
    document.getElementById('btnResetScores')?.addEventListener('click', () => {
        p1Score = 0;
        p2Score = 0;
        if (scoreP1El) scoreP1El.textContent = 0;
        if (scoreP2El) scoreP2El.textContent = 0;
    });

    // بستن مودال‌ها با Escape یا کلیک روی پس‌زمینه
    if (toolsModal) {
        toolsModal.addEventListener('click', (e) => {
            if (e.target === toolsModal) closeModal(toolsModal);
        });
    }

    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') {
            closeAllModals();
            closeModal(toolsModal);
        }
    });
});
