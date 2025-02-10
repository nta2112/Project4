const formTool = document.getElementById("formTool");
const formToolHeader = document.getElementById("formToolHeader");
const formTooledit = document.getElementById("formTool-edit");
const formToolHeaderedit = document.getElementById("formToolHeader-edit");
const overlay = document.getElementById("overlay");

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
formToolHeaderedit.addEventListener("mousedown", (e) => {
  isDragging = true;
  offsetX = e.clientX - formTooledit.offsetLeft;
  offsetY = e.clientY - formTooledit.offsetTop;
  formTooledit.style.cursor = "grabbing";
});

// Khi di chuyển chuột
document.addEventListener("mousemove", (e) => {
  if (isDragging) {
    formTool.style.left = `${e.clientX - offsetX}px`;
    formTool.style.top = `${e.clientY - offsetY}px`;
    formTooledit.style.left = `${e.clientX - offsetX}px`;
    formTooledit.style.top = `${e.clientY - offsetY}px`;
  }
});

// Khi nhả chuột
document.addEventListener("mouseup", () => {
  isDragging = false;
  formTool.style.cursor = "default";
  formTooledit.style.cursor = "default";
});



const closeButton = document.getElementById("closeButton");


// Khi nhấn vào nút đóng
closeButton.addEventListener("click", () => {
  formTool.style.display = "none"; // Ẩn form
  overlay.style.display = "none";
});

const closeButtonedit = document.getElementById("closeButtonedit");

closeButtonedit.addEventListener("click", () => {
  formTooledit.style.display = "none"; // Ẩn form
  overlay.style.display = "none";
});

const openButton = document.getElementById("open_form");

// Khi nhấn vào nút mở form
openButton.addEventListener("click", () => {
  formTool.style.display = "block";
  overlay.style.display = "block";
});

const openButtonedit = document.querySelectorAll(".btn-edit");

openButtonedit.forEach((button) => {
  button.addEventListener("click", () => {
    formTooledit.style.display = "block";
    overlay.style.display = "block";
  });
});

overlay.addEventListener("click", () => {
  formTooledit.style.display = "none";
  formTool.style.display = "none";
  overlay.style.display = "none";
});


// Form
const inputs = document.querySelectorAll('.searchInput');
const dropdowns = document.querySelectorAll('.dropdown');
const readonlyInputs = document.querySelectorAll('.searchInput_option');

// Lặp qua từng bộ input và dropdown
inputs.forEach((input, index) => {
  const dropdown = dropdowns[index];
  const readonlyInput = readonlyInputs[index];

  // Hiển thị dropdown khi nhấp vào ô input tìm kiếm
  input.addEventListener('click', () => {
    dropdown.style.display = 'block';
  });

  // Lọc các lựa chọn trong dropdown khi nhập tìm kiếm
  input.addEventListener('input', () => {
    const searchValue = input.value.toLowerCase();
    Array.from(dropdown.options).forEach((option) => {
      const optionText = option.text.toLowerCase();
      option.style.display = optionText.includes(searchValue) ? '' : 'none';
    });
  });

  // Khi chọn một option, hiển thị text của option trong ô readonly
  dropdown.addEventListener('change', (event) => {
    readonlyInput.value = event.target.options[event.target.selectedIndex].text; // Gán text của option vào ô readonly
    dropdown.style.display = 'none'; // Ẩn dropdown sau khi chọn
  });
});

// Ẩn dropdown khi nhấp ra ngoài
document.addEventListener('click', (event) => {
  inputs.forEach((input, index) => {
    const dropdown = dropdowns[index];
    if (!input.contains(event.target) && !dropdown.contains(event.target)) {
      dropdown.style.display = 'none';
    }
  });
});
