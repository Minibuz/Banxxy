const API_URL = '/api/customer/create/';

class CreateCustomer{
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
                if (! response.ok){
                    // get error message from body or default to response status
                    const error = (data && data.message) || response.status;
                    return Promise.reject(error);
                }
                return response.data;
            })
            .catch(error => {
                console.error("This is an error",error);
            });
    }
}

export default CreateCustomer;