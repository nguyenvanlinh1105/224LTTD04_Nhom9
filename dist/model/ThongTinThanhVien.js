"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const sequelize_1 = require("sequelize");
const database_1 = __importDefault(require("../config/database"));
const ThongTinThanhVien = database_1.default.define("ThongTinThanhVien", {
    maSinhVien: {
        type: sequelize_1.DataTypes.STRING,
        allowNull: false,
        primaryKey: true
    },
    hoTen: {
        type: sequelize_1.DataTypes.STRING,
        allowNull: false
    },
    lop: {
        type: sequelize_1.DataTypes.STRING,
        allowNull: false
    },
    gioiTinh: {
        type: sequelize_1.DataTypes.BOOLEAN,
        allowNull: false
    },
    ngaySinh: {
        type: sequelize_1.DataTypes.DATE,
        allowNull: false
    },
    sdt: {
        type: sequelize_1.DataTypes.STRING,
        allowNull: false
    }
}, {
    tableName: "ThongTinThanhVien",
    timestamps: false
});
exports.default = ThongTinThanhVien;
