import system from '../modules/system/module'
Store.registerModule('system', system.store)
router.addRoutes(system.router)
Store.dispatch('system/initialize', null, { root: true })
