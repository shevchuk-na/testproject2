/*!
 * Ext JS Library 3.4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
Ext.ns('App', 'App.user');
/**
 * @class App.user.FormPanel
 * A typical FormPanel extension
 */
App.user.Form = Ext.extend(Ext.form.FormPanel, {
    renderTo: 'pet-form',
    iconCls: 'silk-user',
    frame: true,
    labelAlign: 'right',
    title: 'Pet form',
    width: 500,
    defaultType: 'textfield',
    defaults: {
        anchor: '100%'
    },

    // private A pointer to the currently loaded record
    record: null,

    /**
     * initComponent
     * @protected
     */
    initComponent: function () {
        // build the form-fields.  Always a good idea to defer form-building to a method so that this class can
        // be over-ridden to provide different form-fields
        this.items = this.buildForm();

        // build form-buttons
        this.buttons = this.buildUI();

        // add a create event for convenience in our application-code.
        this.addEvents({
            /**
             * @event create
             * Fires when user clicks [create] button
             * @param {FormPanel} this
             * @param {Object} values, the Form's values object
             */
            create: true
        });

        // super
        App.user.Form.superclass.initComponent.call(this);
    },


    /**
     * buildform
     * @private
     */
    buildForm: function () {
        return [
            {fieldLabel: 'Name', name: 'name', allowBlank: false},
            {
                xtype: 'combo',
                fieldLabel: 'Animal',
                hiddenName: 'animalName',
                id: 'comboAnimal',
                store: animalStore,
                valueField: 'name',
                displayField: 'name',
                triggerAction: 'all',
                editable: false
            },
            {fieldLabel: 'Weight', xtype: 'numberfield', name: 'weight', allowBlank: false},
            {fieldLabel: 'Adopted', xtype: 'checkbox', name: 'adopted', inputValue: true}
        ];
    },

    /**
     * buildUI
     * @private
     */
    buildUI: function () {
        return [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: this.onUpdate,
            scope: this
        }, {
            text: 'Create',
            iconCls: 'silk-user-add',
            handler: this.onCreate,
            scope: this
        }, {
            text: 'Reset',
            handler: function (btn, ev) {
                this.getForm().reset();
            },
            scope: this
        }];
    },

    /**
     * loadRecord
     * @param {Record} rec
     */
    loadRecord: function (rec) {
        this.record = rec;
        this.getForm().loadRecord(rec);
    },

    /**
     * onUpdate
     */
    onUpdate: function (btn, ev) {
        if (this.record == null) {
            return;
        }
        if (!this.getForm().isValid()) {
            App.setAlert(false, "Form is invalid.");
            return false;
        }
        this.getForm().updateRecord(this.record);
    },

    /**
     * onCreate
     */
    onCreate: function (btn, ev) {
        if (!this.getForm().isValid()) {
            App.setAlert(false, "Form is invalid");
            return false;
        }
        var combo = Ext.getCmp('comboAnimal');
        var record = animalStore.getAt(animalStore.find('name', combo.getValue()));
        var data = this.getForm().getValues();
        data['animalId'] = record.data['id'];
        data['animalDescription'] = record.data['description'];
        if (data['adopted'] !== 'true') {
            data['adopted'] = false;
        }
        this.fireEvent('create', this, data);
        this.getForm().reset();
    },

    /**
     * onReset
     */
    onReset: function (btn, ev) {
        this.fireEvent('update', this, this.getForm().getValues());
        this.getForm().reset();
    }
});

var animalStore = new Ext.data.Store({
    autoLoad: true,
    fields: [
        {name: 'id'},
        {name: 'name', type: 'string'},
        {name: 'description', type: 'string'}
    ],
    proxy: new Ext.data.HttpProxy({
        api: {
            read: 'animals/read'
        }
    }),
    reader: new Ext.data.JsonReader({
        successProperty: 'success',
        idProperty: 'id',
        root: 'data'
    }, [
        {name: 'id', mapping: 'id'},
        {name: 'name', mapping: 'name', type: 'string'},
        {name: 'description', mapping: 'description', type: 'string'}
    ])
});