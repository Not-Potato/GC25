// 글자수 제한 함수
function limitMaxLength(e) {
	if (e.value.length > e.maxLength) {
		e.value = e.value.slice(0, e.maxLength);
		alert('입력 가능한 범위를 초과했습니다!');
	}
}

/*// 별점 함수
let input1 = document.querySelector("#score1 input");
let star1 = document.querySelector("#score1 .fillStar");
		
input1.addEventListener('input', () => {
	star1.style.width = `${input1.value * 10}%`;
	// 값 받기
	console.log("전체 만족도: " + input1.value);
})*/

// 반복문을 사용하여 별점 함수 생성
for (let i = 1; i <= 4; i++) {
  let input = document.querySelector(`#score${i} input`);
  let star = document.querySelector(`#score${i} .fillStar`);

  input.addEventListener('input', () => {
    star.style.width = `${input.value * 10}%`;
    // 값 받기
    console.log(`만족도${i}: ${input.value}`);
  });
}