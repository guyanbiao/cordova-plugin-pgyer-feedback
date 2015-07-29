/*global cordova, module*/

module.exports = {
    popup: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "PgyerPlugin", "popup", [name]);
    }
};
