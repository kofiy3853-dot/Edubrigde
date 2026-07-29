export type Theme = "light" | "dark" | "system";

export const themes: Record<Theme, Record<string, string>> = {
  light: {
    background: "#ffffff",
    foreground: "#0a0a0a",
    muted: "#f5f5f5",
    "muted-foreground": "#737373",
    border: "#e5e5e5",
    primary: "#171717",
    "primary-foreground": "#fafafa",
  },
  dark: {
    background: "#0a0a0a",
    foreground: "#ededed",
    muted: "#262626",
    "muted-foreground": "#a3a3a3",
    border: "#404040",
    primary: "#fafafa",
    "primary-foreground": "#171717",
  },
  system: {
    background: "var(--color-background)",
    foreground: "var(--color-foreground)",
  },
};

export function getThemeClasses(theme: Theme): string {
  if (theme === "system") return "dark:bg-background dark:text-foreground";
  return theme === "dark" ? "dark" : "";
}
