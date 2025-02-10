// Lấy tất cả các nút "btn-see"
const buttons = document.querySelectorAll(".btn-action.btn-see");
// Lấy form cần hiển thị
const formTool = document.getElementById("formTool");
// Lấy nút đóng trong form
const closeFormButton = document.getElementById("closeButton");
const overlay = document.getElementById("overlay");

// Thêm sự kiện click cho từng nút
buttons.forEach((button) => {
  button.addEventListener("click", () => {
    // Hiển thị form khi bấm nút
    formTool.style.display = "block";
    overlay.style.display = "block";
  });
});

// Ẩn form khi bấm nút "Đóng"
closeFormButton.addEventListener("click", () => {
  formTool.style.display = "none";
  overlay.style.display = "none";
});

// Ẩn form khi nhấn ra ngoài form
document.addEventListener("click", (event) => {
  if (event.target === formTool) {
    formTool.style.display = "none";
  }
});

overlay.addEventListener("click", () => {
  formTool.style.display = "none";
  overlay.style.display = "none";
});


// Lấy span trạng thái và nút chuyển đổi
const trangThaidv = document.querySelector(".trang-thai-dv");
const toggleButtondv = document.querySelector(".btn-toggle-status-dv");

// Xác định trạng thái ban đầu dựa trên nội dung
if (trangThaidv.textContent.trim() === "Đã thanh toán") {
  trangThaidv.classList.add("paid"); // Màu xanh cho "Đã thanh toán"
} else if (trangThaidv.textContent.trim() === "Chưa thanh toán") {
  trangThaidv.classList.add("unpaid"); // Màu đỏ cho "Chưa thanh toán"
}

// Gắn sự kiện click cho nút
toggleButtondv.addEventListener("click", () => {
  // Kiểm tra nội dung hiện tại
  if (trangThaidv.textContent.trim() === "Đã thanh toán") {
    // Chuyển sang "Chưa thanh toán"
    trangThaidv.textContent = "Chưa thanh toán";
    trangThaidv.classList.remove("paid");
    trangThaidv.classList.add("unpaid");
  } else {
    // Chuyển sang "Đã thanh toán"
    trangThaidv.textContent = "Đã thanh toán";
    trangThaidv.classList.remove("unpaid");
    trangThaidv.classList.add("paid");
  }
});





// Lấy span trạng thái và nút chuyển đổi
const trangThai = document.querySelector(".trang-thai");
const toggleButton = document.querySelector(".btn-toggle-status");

// Xác định trạng thái ban đầu dựa trên nội dung
if (trangThai.textContent.trim() === "Đã thanh toán") {
  trangThai.classList.add("paid"); // Màu xanh cho "Đã thanh toán"
} else if (trangThai.textContent.trim() === "Chưa thanh toán") {
  trangThai.classList.add("unpaid"); // Màu đỏ cho "Chưa thanh toán"
}

// Gắn sự kiện click cho nút
toggleButton.addEventListener("click", () => {
  // Kiểm tra nội dung hiện tại
  if (trangThai.textContent.trim() === "Đã thanh toán") {
    // Chuyển sang "Chưa thanh toán"
    trangThai.textContent = "Chưa thanh toán";
    trangThai.classList.remove("paid");
    trangThai.classList.add("unpaid");
  } else {
    // Chuyển sang "Đã thanh toán"
    trangThai.textContent = "Đã thanh toán";
    trangThai.classList.remove("unpaid");
    trangThai.classList.add("paid");
  }
});




