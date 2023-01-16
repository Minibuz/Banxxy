const API_URL = '/api/advisor/create/';

class CreateUser{
    static create(customer){
        return fetch(`${API_URL}customer`,{
            method: 'POST',
            body:{
                firstName: customer.firstName,
                lastName: customer.lastName,
                email: customer.email,
                username: customer.username,
                password: customer.password
            }})
            .then(response => {
                if (response.data.accessToken){
                    localStorage.setItem('customer',JSON.stringify(response.data));
                }
                return response.data;
            });
    }
}

export default CreateUser;