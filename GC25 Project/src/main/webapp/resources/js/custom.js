// 글자수 제한 함수
function limitMaxLength(e) {
	if (e.value.length > e.maxLength) {
		e.value = e.value.slice(0, e.maxLength);
		alert('입력 가능한 범위를 초과했습니다!');
	}
}

// 게시글 작성 함수
function posting() {
	
}