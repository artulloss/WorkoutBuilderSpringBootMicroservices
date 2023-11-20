/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./templates/*.html", "./templates/**/*.html"],
  theme: {
    extend: {
      colors: {
        "deep-red": "#8B0000",
        "lighter-red": "#A10A0A",
      },
    },
  },
  plugins: [require("daisyui")],
  daisyui:{
    darkTheme: 'light',
  }
};
