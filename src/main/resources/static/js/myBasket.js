function allCheckFunction(all) {
    const checkboxes = document.getElementsByName("select");

    checkboxes.forEach((checkbox) => {
      checkbox.checked = all.checked;
    })
    console.log(checkboxes);
}