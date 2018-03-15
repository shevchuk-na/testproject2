Ext.onReady(function () {

    var pet = Ext.data.Record.create([
        {name: 'id', mapping: 'id'},
        {name: 'name', mapping: 'name', type:'string', allowBlank: false},
        {name: 'animalId', mapping: 'animal.id'},
        {name: 'animalName', mapping: 'animal.name', type: 'string'},
        {name: 'animalDesc', mapping: 'animal.description', type: 'string'},
        {name: 'weight', mapping: 'weight', type:'float'},
        {name: 'adopted', mapping: 'adopter', type:'boolean'}
        ]);

    var proxy = new Ext.data.HttpProxy({
        api: {
            read: '/pets'
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
                width: 160,
                sortable: true,
                dataIndex: 'weight'
            },
            {
                header: "ADOPTED",
                width: 170,
                sortable: true,
                dataIndex: 'adopted'
            }
        ],
        viewConfig: {forcefit: true},
        title: 'Pets',
        height: 300,
        width: 800,
        frame: true,
    });

    grid.render('grid');
});