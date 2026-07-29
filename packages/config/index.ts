export const config = {
  api: {
    baseUrl: process.env.NEXT_PUBLIC_API_URL || "http://localhost:4000",
    version: "v1",
  },
  app: {
    name: "EduBridge OS",
    url: process.env.NEXT_PUBLIC_APP_URL || "http://localhost:3000",
  },
  auth: {
    tokenKey: "edubridge_auth_token",
    refreshKey: "edubridge_refresh_token",
  },
} as const;

export type Config = typeof config;
