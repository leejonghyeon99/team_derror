document.addEventListener('DOMContentLoaded', function() {
    // 대댓글 작성 버튼 이벤트 처리
    const replyButtons = document.querySelectorAll('.btn-reply');

    replyButtons.forEach(button => {
        button.addEventListener('click', function() {
            const replyForm = this.nextElementSibling;
            replyForm.style.display = (replyForm.style.display === 'none' || replyForm.style.display === '') ? 'block' : 'none';
        });
    });

    // 대댓글 등록 버튼 이벤트 처리
    const submitReplyButtons = document.querySelectorAll('.btn-submit-reply');

    submitReplyButtons.forEach(button => {
        button.addEventListener('click', function() {
            const replyForm = this.parentElement;
            const replyInput = replyForm.querySelector('.reply-input');
            const replyContent = replyInput.value;

            if (replyContent.trim() !== '') {
                const replyItem = document.createElement('div');
                replyItem.classList.add('reply-item');
                replyItem.innerHTML = `
                    <div class="reply-header">
                        <span class="reply-author">작성자명</span>
                        <span class="reply-date">작성일자</span>
                    </div>
                    <div class="reply-content">${replyContent}</div>
                `;

                // 대댓글 목록에 새로운 대댓글 추가
                const replies = this.parentElement.parentElement.querySelector('.replies');
                replies.appendChild(replyItem);

                // 대댓글 입력 폼 초기화
                replyInput.value = '';
                replyForm.style.display = 'none';
            } else {
                alert('대댓글 내용을 입력하세요.');
            }
        });
    });
});
