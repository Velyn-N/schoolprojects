let categories = [];

let categoriesVue = new Vue({
    el: '#categories',
    data: {
        'categories': categories
    }
});

function loadViewData() {
    $.ajax("/rest/categories/all/withnotes", {
        success: function (data) {
            console.log(data);

            categories = data;
            categoriesVue.categories = categories;
        }
    });
}

$(document).ready(function () {
    loadViewData();
});