Ext.onReady(function () {

    var pet = Ext.data.Record.create([
        {name: 'id', mapping: 'id'},
        {name: 'name', mapping: 'name', type: 'string', allowBlank: false},
        {name: 'animalId', mapping: 'animal.id'},
        {name: 'animalName', mapping: 'animal.name', type: 'string'},
        {name: 'animalDesc', mapping: 'animal.description', type: 'string'},
        {name: 'weight', mapping: 'weight', type: 'float'},
        {name: 'adopted', mapping: 'adopted', type: 'boolean'}
    ]);

    var proxy = new Ext.data.HttpProxy({
        api: {
            read: '/pets/read'
        }
    });

    var reader = new Ext.data.JsonReader({
            totalProperty: 'total',
            successProperty: 'success',
            idProperty: 'id',
            root: 'data',
            messageProperty: 'message'
        },
        pet);

    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
    });

    var store = new Ext.data.Store({
        id: 'user',
        proxy: proxy,
        reader: reader,
        writer: writer,
        autoSave: false
    });

    store.load();

    Ext.data.DataProxy.addListener('exception', function (proxy, type, action, options, res) {
        Ext.Msg.show({
            title: 'ERROR',
            msg: res.message,
            icon: Ext.MessageBox.ERROR,
            buttons: Ext.Msg.OK
        });
    });

    var tb = new Ext.Toolbar({
        width: 600,
        height: 30,
        items: [
            {
                text: 'Add',
                handler: function () {

                }
            },
            {
                text: 'Edit'
            },
            {
                text: 'delete'
            }
        ]
    });

    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
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
        ],
        viewConfig: {forcefit: true},
        title: 'Pets',
        height: 300,
        width: 800,
        frame: true,
        tbar: tb
    });

    grid.render('grid');
});
// Ext.onReady(function () {
//
//     var petCM = new Ext.grid.ColumnModel({
//         columns: [
//             {header: "Name", width: 60, menuDisabled: false},
//             {header: "Animal", width: 150, id: 'company'},
//             {header: "Weight", renderer: 'number'},
//             {header: "Adopted?", xtype:'booleancolumn'}
//         ],
//         defaults: {
//             sortable: true,
//             menuDisabled: true,
//             width: 100
//         },
//         listeners: {
//             // hiddenchange: function(cm, colIndex, hidden) {
//             //     saveConfig(colIndex, hidden);
//             // }
//         }
//     });
//
//     var petProxy = new Ext.data.HttpProxy({
//         api: {
//             read: 'pets/read',
//             create: 'pets/add'
//             // update: 'app.php/users/update',
//             // destroy: 'app.php/users/destroy'
//         }
//     });
//
//     var pet = Ext.data.Record.create([
//         {name: 'id', mapping: 'id'},
//         {name: 'name', mapping: 'name', type:'string', allowBlank: false},
//         {name: 'animalId', mapping: 'animal.id'},
//         {name: 'animalName', mapping: 'animal.name', type: 'string'},
//         {name: 'animalDesc', mapping: 'animal.description', type: 'string'},
//         {name: 'weight', mapping: 'weight', type:'float'},
//         {name: 'adopted', mapping: 'adopter', type:'boolean'}
//     ]);
//
//     var petReader = new Ext.data.JsonReader({
//         successProperty: 'success',
//         idProperty: 'id',
//         root: 'data'
//     }, pet);
//
//
//     var petWriter = new Ext.data.JsonWriter({
//         encode: true,
//         writeAllFields: false
//     });
//
//
//     var petStore = new Ext.data.Store({
//         id: 'petStore',
//         proxy: petProxy,
//         reader: petReader,
//         writer: petWriter,
//         autoSave: true
//     });
//
//     petStore.load();
//
//     var petGrid = new Ext.grid.GridPanel({
//         store: petStore,
//         cm: petCM,
//         viewConfig: {forcefit: true}
//     });
//
//     var tabs = new Ext.TabPanel({
//         renderTo: document.body,
//         activeTab: 0,
//         width: 600,
//         height: 250,
//         plain: true,
//         defaults: {autoScroll: true},
//         items: [{
//             title: 'Pets',
//             items: petGrid,
//             border: false,
//             layout: 'fit'
//         // }, {
//         //     title: 'Pets',
//         //     items: animalGrid,
//         //     border: false,
//         //     layout: 'fit'
//         }
//         ]
//     });
// });
