
function fetchIngredient(_id) {
    fetch(`http://127.0.0.1:5000/api/ingredient/id/${_id}`)
        .then(response => response.json())
        .then(ingredient => {
            $("#_id").val(ingredient._id);
            $("#name").val(ingredient.name);
            $("#price").val(ingredient.price);

        });
}

function loadInformation() {
    let urlParams = new URLSearchParams(window.location.search);
    let _id = urlParams.get('_id');
    fetchIngredient(_id)
}

function putIngredient(ingredient) {

    fetch('http://127.0.0.1:5000/api/ingredient', {
        method: 'PUT',
        body: JSON.stringify(ingredient),
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
    })
        .then(res => res.json())
        .then(res => showNotification());


}

/**
 * Get the form and submit it with fetch API
 */
let ingredientForm = $("#ingredient-form");
ingredientForm.submit(event => {

    let ingredient = getIngredientData();
    putIngredient(ingredient);

    event.preventDefault();
    event.currentTarget.reset();
    window.location.href = '/app/ingredient/ingredients.html';
});

/**
 * Gets the ingredient data with JQuery
 */
function getIngredientData() {
    return {
        _id: $("input[id='_id']").val(),
        name: $("input[id='name']").val(),
        price: $("input[id='price']").val()
    };
}

/**
 * Shows a notification when the ingredient is accepted
 */
function showNotification() {
    let ingredientAlert = $("#ingredient-alert");
    ingredientAlert.toggle();
    setTimeout(() => ingredientAlert.toggle(), 5000);
}


window.onload = loadInformation;