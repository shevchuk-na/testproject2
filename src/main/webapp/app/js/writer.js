var App = new Ext.App({});

var proxy = new Ext.data.HttpProxy({
    api: {
        read: 'pets/read',
        create: 'pets/add',
        update: 'pets/update',
        destroy: 'pets/remove'
    }
});

var reader = new Ext.data.JsonReader({
    successProperty: 'success',
    idProperty: 'id',
    root: 'data'
}, [
    {name: 'id', mapping: 'id'},
    {name: 'name', mapping: 'name', type: 'string', allowBlank: false},
    {name: 'animalId', mapping: 'animal.id'},
    {name: 'animalName', mapping: 'animal.name', type: 'string'},
    {name: 'animalDescription', mapping: 'animal.description', type: 'string'},
    {name: 'weight', mapping: 'weight', type: 'float'},
    {name: 'adopted', mapping: 'adopted', type: 'boolean'}
]);

var writer = new Ext.data.JsonWriter({
    encode: true,
    writeAllFields: true
});

var store = new Ext.data.Store({
    id: 'pet',
    proxy: proxy,
    reader: reader,
    writer: writer,
    autoSave: false
});

store.load();

var userColumns = [
    {
        header: "NAME",
        width: 170,
        sortable: true,
        dataIndex: 'name'
    },
    {
        header: "ANIMAL",
        width: 160,
        sortable: true,
        dataIndex: 'animalName'
    },
    {
        header: "WEIGHT",
        dataIndex: 'weight',
        width: 160,
        sortable: true,
        xtype: 'numbercolumn'
    },
    {
        header: "ADOPTED",
        width: 170,
        sortable: true,
        dataIndex: 'adopted',
        xtype: 'booleancolumn'
    }
];

Ext.onReady(function () {
    Ext.QuickTips.init();

    var userForm = new App.user.Form({
        renderTo: 'pet-form',
        listeners: {
            create: function (fpanel, data) {   // <-- custom "create" event defined in App.user.Form class
                var rec = new userGrid.store.recordType(data);
                userGrid.store.insert(0, rec);
            }
        }
    });

    // create user.Grid instance (@see UserGrid.js)
    var userGrid = new App.user.Grid({
        renderTo: 'pet-grid',
        store: store,
        columns: userColumns,
        listeners: {
            rowclick: function (g, index, ev) {
                var rec = g.store.getAt(index);
                userForm.loadRecord(rec);
            },
            destroy: function () {
                userForm.getForm().reset();
            }
        }
    });
});
