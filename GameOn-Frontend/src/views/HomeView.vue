<template>
    <ul class="gameGrid">
        <li v-for="gameResponse in filteredGameResponses" :key="gameResponse.name">
            <MainPageGame :gameResponse="gameResponse"/>
        </li>
    </ul>
</template>

<script>
import {mapState, mapGetters} from 'vuex'

import MainPageGame from "../components/MainPageGame.vue";
import { Game } from "../dto/Game";
import { GameResponseDTO } from "../dto/GameResponseDTO";


export default {
  name: "HomeView",
  components: {
    MainPageGame,
  },
  data() {
    return {
      gameResponses: [],
    };
  },
  async mounted() {
    const response = await Game.findAllGames();
    this.gameResponses = response.map(response => new GameResponseDTO(response));
    console.log("Games: ");
    console.log(this.gameResponses);
  },
  computed: {
    filteredGameResponses() {
      console.log("Shared category: "); console.log(this.sharedCategory)
      console.log("Shared game: "); console.log(this.sharedSearch)
      return this.gameResponses.filter(game =>
        (this.sharedSearch === '' || !this.sharedSearch || game.name.toLowerCase().startsWith(this.sharedSearch.toLowerCase()))
          && (  !this.sharedCategory || this.sharedCategory === '' || this.sharedCategory === game.category)
      );
    },
    ...mapGetters(['getSharedSearch']), // Map Vuex getter
    sharedSearch() {
      return this.getSharedSearch; // Access the shared state
    },
    ...mapGetters(['getSharedCategory']), // Map Vuex getter
    sharedCategory() {
      return this.getSharedCategory; // Access the shared state
    }
  }
};
</script>

<style scoped>
.gameGrid {
    display: flex;
    margin: 0 0;
    padding: 0 0;
    flex-wrap: wrap;

    position: relative;
    left : 0;
    top:0;
    height:auto;
    width:100%;
    list-style: none;

}
.gameGrid li {
    display: flex;
    object-fit: contain;
    width: 20%;
    aspect-ratio: 1 ;
}

</style>
