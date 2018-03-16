/*!
 * Ext JS Library 3.4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
Ext.ns('App', 'App.user');
/**
 * App.user.Grid
 * A typical EditorGridPanel extension.
 */
App.user.Grid = Ext.extend(Ext.grid.EditorGridPanel, {
    renderTo: 'pet-grid',
    iconCls: 'silk-grid',
    frame: true,
    title: 'Users',
    height: 300,
    width: 500,
    style: 'margin-top: 10px',

    initComponent: function () {

        // typical viewConfig
        this.viewConfig = {
            forceFit: true
        };

        // relay the Store's CRUD events into this grid so these events can be conveniently listened-to in our application-code.
        this.relayEvents(this.store, ['destroy', 'save', 'update']);

        // build toolbars and buttons.
        this.tbar = this.buildTopToolbar();
        this.buttons = this.buildUI();

        // super
        App.user.Grid.superclass.initComponent.call(this);
    },

    /**
     * buildTopToolbar
     */
    buildTopToolbar: function () {
        return [{
            text: 'Delete',
            iconCls: 'silk-delete',
            handler: this.onDelete,
            scope: this
        }, '-'];
    },

    /**
     * buildUI
     */
    buildUI: function () {
        return [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: this.onSave,
            scope: this
        }];
    },

    /**
     * onSave
     */
    onSave: function (btn, ev) {
        this.store.save();
    },

    /**
     * onDelete
     */
    onDelete: function (btn, ev) {
        var index = this.getSelectionModel().getSelectedCell();
        if (!index) {
            return false;
        }
        var rec = this.store.getAt(index[0]);
        this.store.remove(rec);
    }
});
