<template>
  <div class="container mt-3">
    <div class=" ">
      <h3 class="mb-4 mt-5">로그인</h3>
      <hr />
      <!-- 로그인 회원가입 라인 -->
      <div class="row">
        <!-- 로그인 시작 -->
        <div class="col log col-divider">
          <h5 class="mb-4 mt-4 log-form"><strong>회원 로그인</strong></h5>
          
            <div class="mb-3">
              <label class="insert-id log-form" for="id">아이디</label>
              <input
                class="form-control log-form"
                type="text"
                placeholder="ID"
                name="id"
                id="id"
                v-model="user.userId"
              />
            </div>
            <div class="mb-3">
              <label class="form-label log-form" for="pwd">비밀번호</label>
              <input
                class="form-control log-form"
                type="password"
                placeholder="PASSWORD"
                name="pwd"
                id="pwd"
                v-model="user.password"
              />
            </div>

            <div class="mb-3">
              <button
                class="btn text-light btn-sm mt-4 log-form"
                id="login-bt"
                type="submit"
                @click="handleLogin"
              >
                로그인
              </button>
            </div>
        </div>
        <!-- 회원가입 시작 -->
        <div class="col join">
          <h5 class="mb-1 mt-4 join-form"><strong>회원가입</strong></h5>
          
            <div class="mb-3 join-form">
              <button
                class="btn text-light btn-sm mt-4"
                id="login-bt"
                type="submit"
                @click="goJoin"
              >
                회원가입
              </button>
              <div class="mt-5">
                <h5><strong>ID/PW 찾기</strong></h5>
              </div>
            </div>
            <div class="join-form mt-0">
              <button class="btn btn-sm" id="find-idpw" type="button" @click="goFindId">
                아이디 찾기
              </button>
              <button class="btn btn-sm mt-4 mb-3" id="find-idpw" type="button" @click="goFindPwd">
                비밀번호 찾기
              </button>
            </div>
          
        </div>
      </div>
      <!-- 소셜로그인 -->
      <div class="row text-center mt-5">
        <div>
          <button class="btn btn-custom naver mt-4 mb-3" id="naver-login-btn" type="submit">
            <img src="@/assets/img/N.png">     &nbsp;&nbsp;        네이버 로그인 / 회원가입
          </button>
        </div>
      </div>
      <div class="row text-center">
        <div>
          <button class="btn btn-custom naver mt-4 " id="kakao-login-btn" type="submit">
            <img src="@/assets/img/K.png">     &nbsp;&nbsp;        카카오 로그인 / 회원가입
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import AuthService from '@/services/auth/AuthService';
export default {
  data() {
    return {
      user:{
        userId: "",
        password:"",
      }
    }
  },
  methods: {
    goFindId(){
      this.$router.push("/member/find-id")
    },
    goJoin(){
      this.$router.push("/member/join")
    },
    goFindPwd(){
      this.$router.push("/member/find-pwd")
    },
    async handleLogin(){
        // 공통 로그인 서비스 함수
        // 로그인 성고 =>
        // 로그인 실해 => 로그인 실패 공유함수 실행
        try {
          let response = await AuthService.login(this.user);
          console.log(response.data);
          localStorage.setItem("user", JSON.stringify(response.data)); // fh칼호스트는 객체를 저장할 수 없기에 이걸 문자열러 바꿔서 진행해야한다.
          this.$store.commit("loginSuccess", response.data);
          this.$router.push("/");
        } catch (e) {
          // 로그인 실패시 에러가 뜨므로 로그인 실패 공유함수를 실행
          this.$store.commit("loginFailure"); // 공유함수의 mutation함수는commit으로 실행한다.
          console.log(e);
        }
      }
  },
};
</script>
<style>
#id {
  width: 483px;
  height: 65px;
}
#pwd {
  width: 483px;
  height: 65px;
}
#login-bt {
  width: 483px;
  height: 65px;
  background-color: #342a26;
}
#find-idpw {
  width: 483px;
  height: 65px;
  background-color: white;
  border-color: black;
  color: black;
}
#naver-login-btn{
  width: 483px;
  height: 65px;
  background-color: white;
  border-color: black;
  color: black;
}
#kakao-login-btn{
  width: 483px;
  height: 65px;
  background-color: white;
  border-color: black;
  color: black;
  margin-bottom: 7vw;
}
.col-divider {
  border-right: 1px solid #ccc; /* 수직선 스타일 설정 */
  padding-right: 0.7813vw; /* 선택적으로 간격을 추가할 수 있습니다. */
}
.log {
  margin-top: 3.6458vw;
}
.join {
  margin-top: 3.6458vw;
}
.log-form {
  margin-left: 3.6458vw;
}
.join-form {
  margin-left: 3.6458vw;
}
.naver{
  color: #4CBB18;
  font-size: 20px;
}
.naver img{
  width: 25px; /* 이미지의 너비를 원하는 크기로 조절합니다. */
}
</style>
