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
                password: customer.password,
                advisorId: customer.advisorId
            }})
            .then(response => {
                if (response.ok){
                    console.log("successful",response)
                }
            })
            .catch(error => {
                console.error("This is an error",error);
            });
    }
}

export default CreateCustomer;