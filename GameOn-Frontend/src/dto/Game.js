import axios from "axios";

export class Game {
  constructor(picture, name, description, price, quantity, category) {
    this.picture = picture;
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.category = category;
  }
  async createGame() {
    const path = "/games";
    try {
      const response = await axios.post(path, this);
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
  static async deleteGame(name) {
    const path = "/games/" + name;
    try {
      const response = await axios.delete(path);
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
  static async findAllGames() {
    const path = "/games";
    try {
      const response = await axios.get(path);
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
  static async findGameByName(name) {
    const path = "/games/" + name;
    try {
      const response = await axios.get(path);
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
  static async findGameByCategory(category) {
    const path = "/games/category/" + category;
    try {
      const response = await axios.get(path);
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
  static async updateGamePrice(name, price) {
    const path = "/games/updatePrice";
    try {
      const response = await axios.post(path, { name, price });
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
  static async updateGameQuantity(name, quantity) {
    const path = "games/updateQuantity";
    try {
      const response = await axios.post(path, { name, quantity });
      return response.data;
    } catch (error) {
      return { error: error.message };
    }
  }
}