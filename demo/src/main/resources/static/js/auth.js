// 인증 관련 공통 함수

// Access Token 만료 시 자동 갱신 후 재요청하는 fetch 래퍼
async function authFetch(url, options = {}) {
    let response = await fetch(url, { ...options, credentials: 'include' });

    // 401이면 Refresh Token으로 Access Token 갱신 시도
    if (response.status === 401) {
        const refreshResponse = await fetch('/refresh', {
            method: 'POST',
            credentials: 'include'
        });

        if (refreshResponse.ok) {
            // 갱신 성공 → 원래 요청 재시도
            response = await fetch(url, { ...options, credentials: 'include' });
        } else {
            // 갱신 실패 → 로그인 페이지로 이동
            window.location.href = '/';
            return response;
        }
    }

    return response;
}

// 로그아웃
async function logout() {
    await fetch('/logout', { method: 'POST', credentials: 'include' });
    window.location.href = '/';
}
