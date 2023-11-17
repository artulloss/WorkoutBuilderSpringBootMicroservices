export const toast = {
    info: async function (message, timeout) {
      return await this._generic(message, "info", timeout);
    },
    success: async function (message, timeout) {
      return await this._generic(message, "success", timeout);
    },
    warning: async function (message, timeout) {
      return await this._generic(message, "warning", timeout);
    },
    error: async function (message, timeout) {
      return await this._generic(message, "error", timeout);
    },
    _generic: async function (message, type, timeout) {
      // Get the toast container element
      const toast = document.getElementById("toast") || this._createToastContainer();
      const alert = document.createElement("div");
      alert.classList.add("alert");
      alert.classList.add(`alert-${type}`);
      alert.innerHTML = `<span>${message}</span>`;
      toast.appendChild(alert);
      // Wait for the specified timeout then remove the toast
      await new Promise((resolve) => setTimeout(resolve, timeout));
      alert.remove();
    },
    _createToastContainer: function () {
      const toast = document.createElement("div");
      toast.id = "toast";
      toast.classList.add("toast");
      document.body.appendChild(toast);
      return toast;
    },
  };
  