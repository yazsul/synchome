const path = require("path");
module.exports = {
  darkMode: "class",
  mode: "jit",
  content: [
    "./index.html",
    './public/**/*.html',
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    screens: {
      xs: "320px",
      // => @media (min-width: 320px) { ... }

      sm: "640px",
      // => @media (min-width: 640px) { ... }

      md: "768px",
      // => @media (min-width: 768px) { ... }

      lg: "1024px",
      // => @media (min-width: 1024px) { ... }

      xl: "1080px",
      // => @media (min-width: 1280px) { ... }

      "2xl": "1281px",
      // => @media (min-width: 1080px) { ... }

      "3xl": "1536px",
      // => @media (min-width: 1536px) { ... }
    },
    fontFamily: {
      body: ["Roboto"],
      diplay: ["Roboto"],
      sans: ["Roboto"],
      serif: ["Roboto"],
    },

    extend: {
      backgroundImage: {},
      lineClamp: {
        7: "7",
        8: "8",
        9: "9",
        10: "10",
      },
      fontSize: {
        md: ["1.125rem", "1.5rem"],
      },
      maxWidth: {
        six: "102rem",
      },
      height: {
        nav: "5.25rem",
      },

      flex: {
        six: "1 0 16.6%",
      },
      dropShadow: {
        full: "0 3px 1px -2px rgba(0,0,0, 0.2), 0 2px 2px 0 rgba(0,0,0,0.14), 0 1px 5px 0 rgba(0,0,0,0.12) ",
      },
      colors: {
        primary: "#009688",
        "primary-2": "#80CBC4",
        "primary-3": "#009688",
        "primary-4": "#B3E0DB",
        "primary-5": "#E6F5F3",
        "primary-6": "#045750",
        "primary-lite": "#6ec8c0",
        secondary: "#444f60",
        "secondary-2": "#556987",
        "secondary-3": "#C1C1C1",
        "secondary-4": "#C7CACF",
        "secondary-5": "#444444",
        "secondary-6": "#697280",
        "secondary-7": "#ECECEC",
        "secondary-8": "#ffffff",
        "secondary-9": "#596474",
        "secondary-10": "#E5E5E5",
        "secondary-11": "#E1E1E1",
        "secondary-lite": "#f1f1f1",
        "secondary-lite-2": "#f5f5f5",
        "secondary-lite-3": "#F9FAFB",
        "secondary-dark": "#313131",
        "hahu-gray": "#444F60",
        "hahu-border": "#D1D5DB",
        "hahu-red": "#991B1B",
        "hahu-red-2": "#DC2626",
        "hahu-red-3": "#FEE2E2",
        "hahu-blue": "#2563EB",
        "pp-blue": "#07211d",

        // dark theme colors

        "primary-dark": "#02201D",
        "primary-dark-2": "#ECEDEF",
        "secondary-dark-2": "#1B2637",
      },
    },
  },
  variants: {
    extend: {
      backgroundOpacity: ["disabled"],
      cursor: ["disabled"],
      borderWidth: ["last", "focus-within", "first"],
      borderRadius: ["hover"],
    },
  },
  plugins: [
    require("@tailwindcss/forms"),
    // require('@tailwindcss/typography'),
    require("@tailwindcss/line-clamp"),
  ],
};
