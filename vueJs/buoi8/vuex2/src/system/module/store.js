import Store from '../../../services/store'
import Router from '../../../services/router'

export default {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    initialize ({ dispatch }) {
      console.info('System initializing...')
      console.info('System initialized.')
    },
    initializeModule ({ dispatch }, module) {
      Store.registerModule(module.name, module.store)
      Router.addRoutes(module.routes)
      dispatch(module.name + '/initialize', null, { root: true })
    }
  }
}
