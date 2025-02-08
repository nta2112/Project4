document.querySelectorAll(".remove_input").forEach(button => {
  button.addEventListener("click", function() {
      this.parentElement.remove();
  });
});

document.getElementById("addInput").addEventListener("click", function() {
  let template = document.getElementById("template");
  let newInput = template.cloneNode(true);
  newInput.style.display = "flex";  // Hiển thị input mới
  newInput.removeAttribute("id");

  // Gắn lại sự kiện click cho phần tử Xóa của input mới
  newInput.querySelector(".remove_input").addEventListener("click", function() {
      newInput.remove();
  });

  document.getElementById("slotInput").appendChild(newInput);
});
