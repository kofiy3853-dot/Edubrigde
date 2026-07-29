export const APP_NAME = "EduBridge OS";
export const APP_DESCRIPTION =
  "Enterprise education platform connecting students with universities";

export const API_VERSION = "v1";

export const MAX_PAGE_SIZE = 100;
export const DEFAULT_PAGE_SIZE = 10;

export const STORAGE_KEYS = {
  AUTH_TOKEN: "edubridge_auth_token",
  REFRESH_TOKEN: "edubridge_refresh_token",
  THEME: "edubridge_theme",
} as const;

export const ROUTES = {
  HOME: "/",
  LOGIN: "/login",
  REGISTER: "/register",
  DASHBOARD: "/dashboard",
  PROFILE: "/profile",
  UNIVERSITIES: "/universities",
  SCHOLARSHIPS: "/scholarships",
  COURSES: "/courses",
  SETTINGS: "/settings",
} as const;

export const HTTP_STATUS = {
  OK: 200,
  CREATED: 201,
  NO_CONTENT: 204,
  BAD_REQUEST: 400,
  UNAUTHORIZED: 401,
  FORBIDDEN: 403,
  NOT_FOUND: 404,
  CONFLICT: 409,
  UNPROCESSABLE_ENTITY: 422,
  TOO_MANY_REQUESTS: 429,
  INTERNAL_SERVER_ERROR: 500,
} as const;
