
export interface UserPostRequest {
    email: string;
    password: string;
};


export type UserResponse = {
    userId?: any | null 
    email: string;
}

export enum UserRoles {
    USER = "USER",
    ADMIN = "ADMIN"
}
