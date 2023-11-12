// store.js
import Vuex from "vuex";

export default new Vuex.Store({
  state: {
    selectedTeam: null,
  },
  mutations: {
    setSelectedTeam(state, teamName) {
      state.selectedTeam = teamName;
    },
  },
});
