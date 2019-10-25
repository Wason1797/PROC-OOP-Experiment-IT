
fetch('http://127.0.0.1:5000/api/size')
    .then(response => response.json())
    .then(sizes => {
        let rows = sizes.map(element => createSizeTemplate(element));
        let table = $("#sizes tbody");
        table.append(rows);
    });


function createSizeTemplate(size) {
    let template = $("#size-item-template")[0].innerHTML;
    return Mustache.render(template, size);
}
