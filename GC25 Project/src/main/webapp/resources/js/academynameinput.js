let $search = document.querySelector("#academyName");
let $none = document.querySelector("#none");
let $auto = document.querySelector(".autoComplete");

$search.onkeyup = (event) => {
	$none.innerHTML = "";
	// 검색어
	let keyword = $search.value.trim();
	/* let json = null; */
	console.log("키워드: " + keyword);
	let matchDataList = null;
	
	// ajax -> 자바 -> DB 값 가져오기
	if (!keyword == "") {
		$none.classList.replace('d-none', 'autoComplete');
		$.ajax({
			url: "/academy/search",
			method: "get",
			data : "keyword="+keyword,
			dataType : "json",
			
			success : function(data){
				matchDataList = data.list;

				console.log(data);
				console.log("[검색 결과] 가져온 배열의 길이: " + matchDataList.length);
						
				switch (event.keyCode) {
					// UP KEY
					case 38:
			            nowIndex = Math.max(nowIndex - 1, 0);
			            break;
		
			        // DOWN KEY
			        case 40:
			            nowIndex = Math.min(nowIndex + 1, matchDataList.length - 1);
			            break;
				
			        // ENTER KEY
			        case 13:
			            let select = matchDataList[nowIndex].academyName;
			            $search.value = select;
			            console.log(select);
			            // 초기화
			            nowIndex = 0;
			            matchDataList.length = 0;
			            break;
				
			        // 그외 다시 초기화
			        default:
			            nowIndex = 0;
			            break;
			    }
			    // 리스트 보여주기
			    showList(matchDataList, keyword, nowIndex);
			    
			    if (data == null) {
					$none.classList.add("d-none"); // ajax 통해 가져온 데이터가 없을 때 결과 창 숨기기
				}
			},
			
			error: function(xhr, status, error) {
				console.log("에러입니다 . . 들어온 키워드는 [" + keyword + "]입니다.");					
			}
		});
	}
};

$search.onblur = () => {
  $none.classList.add("d-none"); // 포커스를 잃었을 때 검색 결과 창 숨기기
};
	
const showList = (data, keyword, nowIndex) => {
    console.log(keyword);
    $none.innerHTML = "";

    // 정규식으로 변환
    let regex = new RegExp(`(${keyword})`, "g");
    
    $none.innerHTML = data
      .map(
        (label, index) => `
        <div class='${nowIndex === index ? "active" : ""}'>
          ${(label.academyName).replace(regex, '<mark>$1</mark>')}
        </div>
      `
      )
      .join("");
};