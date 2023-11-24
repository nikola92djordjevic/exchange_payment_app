export default{

  function: {


    async getAllUsers() {

      const response = await fetch('/api/user/getAllUsersPersonalInfo', {
        headers: {
          Authorization: sessionStorage.getItem('user-token')
        }
      });
      return await response.json();
    },
    async getRoles() {

      const response = await fetch('/api/user/getRoles', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json())

      return await response
    },

    createNewUser(obj) {


     },
  }
}
