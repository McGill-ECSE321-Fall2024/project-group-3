export class Order {
  static async getOrder(id) {
    const path = "/orders/" + id;
    try {
      const response = await axios.get(path);
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
  static async createOrder(cartId) {
    const path = "createOrder/" + cartId;
    try {
      const response = await axios.post(path);
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
}