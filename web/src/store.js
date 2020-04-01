import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {

  },
  state: {
    enterpriseId: null,
    enterpriseInfo: null,
  },
  getters: {
    getEnterpriseId: state => {
      return state.enterpriseId
    },
  },
  mutations: {
    reduceEnterpriseId: (state, data) => {
      state.enterpriseId = data
    },
  },
  actions: {
    setEnterpriseId: (context, data) =>{
      context.commit('reduceEnterpriseId', data)
    },
  },
  plugins: [createPersistedState()]
})
