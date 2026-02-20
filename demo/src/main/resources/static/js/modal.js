// 모달 HTML 자동 삽입
(function() {
    const modalHtml = `
        <div class="modal-overlay" id="customModal">
            <div class="modal-box">
                <div class="modal-icon" id="modalIcon"></div>
                <div class="modal-message" id="modalMessage"></div>
                <button class="modal-btn" id="modalBtn" onclick="closeModal()">확인</button>
            </div>
        </div>
    `;
    document.body.insertAdjacentHTML('beforeend', modalHtml);
})();

let modalCallback = null;
const icons = { success: '\u2714', error: '\u2718', warning: '\u26A0' };

function showModal(message, type, callback) {
    const modal = document.getElementById('customModal');
    const icon = document.getElementById('modalIcon');
    const msg = document.getElementById('modalMessage');
    const btn = document.getElementById('modalBtn');

    icon.className = 'modal-icon ' + type;
    icon.textContent = icons[type] || icons.warning;
    msg.textContent = message;
    btn.className = 'modal-btn ' + type;
    modal.classList.add('show');
    modalCallback = callback || null;
}

function closeModal() {
    document.getElementById('customModal').classList.remove('show');
    if (modalCallback) {
        modalCallback();
        modalCallback = null;
    }
}
