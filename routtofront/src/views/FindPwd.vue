<template>
  <div class="container">
    <br />
    <br />
    <br />
    <br />
    <br />
    <h2 class="text-center">비밀번호 찾기</h2>
    <div class="container">
      <div class="row justify-content-md-center">
        <div class="col-8">
          <div class="mt-5" id="comment">
            <p>＊회원가입시 입력한 비밀번호 확인 질문과 답변으로 비밀번호 찾기가 가능합니다.<br>
                ＊회원 ID와, 비밀번호 확인 질문과 답변을 입력해주세요.</p>
          </div>
          <div class="mt-5" id="box">
            <div class="col-11">
            
              <label class="form-label" for="role">회원 유형</label>
              <select class="form-select" aria-label="Default select example">
                <option selected>회원</option>
                <option value="1">관리자</option>
              </select>
            </div>
            <div class="mt-4 col-11">
              <label class="form-label" for="id">회원 ID</label>
              <input class="form-control" type="text" name="id" />
            </div>
            <div class="mt-4 col-11">
              <label class="form-label" for="address"
                  >비밀번호 확인 질문</label
                >
              <select class="form-select" aria-label="Default select example">
                  <option selected>자신이 가장 존경하는 인물은?</option>
                  <option value="1">기억에 남는 추억의 장소는?</option>
                  <option value="2">자신의 인생 좌우명은?</option>
                  <option value="3">인상깊게 읽은 책 이름은?</option>
                </select>
            </div>
            <div class="mt-4 col-11">
              <label class="form-label" for="pwdAskCheck"
                  >비밀번호 질문 확인</label
                >
                <input class="form-control" type="text" name="pwdAskCheck" />
            </div>
            
           
          </div>
        </div>
      </div>
    </div>
    <div class="col-3"></div>
  </div>
  <br />
  <br />
  <div class="container text-center">
    <div class="row justify-content-md-center">
      <div class="col-md-auto">
        <button
          class="text-light findPwdBtn btn-sm mt-4"
          id=""
          type="submit"
        >
        확인
        </button>
      </div>
    </div>
  </div>
  <br />
  <br />
  <br />
</template>
<script>
export default {};
</script>
<style>
#box {
  border: 1px solid #cccccc;
  padding: 60px 100px 60px 80px;
}
.findPwdBtn {

background-color: #342a26;
color: white;
font-size: 20px;
width: 200px;
height: 50px;
}
</style>
