const formTool = document.getElementById("formTool");
const formToolHeader = document.getElementById("formToolHeader");

let isDragging = false;
let offsetX = 0;
let offsetY = 0;

// Khi nhấn chuột vào phần tiêu đề
formToolHeader.addEventListener("mousedown", (e) => {
  isDragging = true;
  offsetX = e.clientX - formTool.offsetLeft;
  offsetY = e.clientY - formTool.offsetTop;
  formTool.style.cursor = "grabbing";
});

// Khi di chuyển chuột
document.addEventListener("mousemove", (e) => {
  if (isDragging) {
    formTool.style.left = `${e.clientX - offsetX}px`;
    formTool.style.top = `${e.clientY - offsetY}px`;
  }
});

// Khi nhả chuột
document.addEventListener("mouseup", () => {
  isDragging = false;
  formTool.style.cursor = "default";
});
const closeButton = document.getElementById("closeButton");

// Khi nhấn vào nút đóng
closeButton.addEventListener("click", () => {
  formTool.style.display = "none"; // Ẩn form
});

const openButton = document.getElementById("open_form");

// Khi nhấn vào nút đóng
openButton.addEventListener("click", () => {
  formTool.style.display = "block"; // Ẩn form
});
