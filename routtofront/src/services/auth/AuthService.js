import http from '@/utils/http-common.js'

class AuthService{
    // 로그인 공통 서비스 함수 정의 
    login(user){
        // 임시 객체 만들기,보안을 위해 post로 이것을 백엔드로전달한다. 
        let data = { 
            userId : user.userId, // 로그인id
            password: user.password, // 암호
        };
        return http.post("/auth/login", data);
    }

    logout(){
        // 로컬 스토리지의 값을 삭제
        // 사용법 : localStorage.removeIItem("키이름")
        localStorage.removeItem("user");
    }

    register(user){
        let data = {
            userId : user.userId,
            password: user.password,
            name: user.userName,
            birthday: user.birthday,
            phoneNum: user.phoneNum,
            callNum: user.callNum,
            email: user.email,
            role: user.role,
            normalAddress: user.normalAddress,
            detailAddress: user.detailAddress,
        }
        return http.post("/autg/register", data);
    }
}

export default new AuthService();