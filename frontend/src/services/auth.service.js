const API_URL = '/api/auth/';

class AuthService {
    login(user) {
        return fetch(API_URL + 'signin', {
            method: "POST",
            body:{
                username: user.username,
                password: user.password
            }})
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

}

export default new AuthService();