import authHeader from "@/services/auth-header";

const API_URL = '/api/user/customer/create/';

class CreateCustomer{
    static create(customer){
        return fetch(`${API_URL}`,{
            method: 'POST',
            headers: authHeader(),
            body:{
                firstName: customer.firstName,
                lastName: customer.lastName,
                mail: customer.mail,
                username: customer.username,
                password: customer.password,
                advisorId: customer.advisorId
            }})
            .then(response => {
                if (response.ok){
                    console.log("successful",response)
                }
            })
            //TODO

            // .catch(error => {
            //
            //     // console.log("There is an error",error);
            //     console.log("There is an error",error);
            // });


    }
}

export default CreateCustomer;