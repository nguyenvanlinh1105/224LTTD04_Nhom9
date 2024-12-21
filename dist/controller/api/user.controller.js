"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.deleteUser = exports.editUser = exports.addUser = exports.detailUser = exports.listUser = void 0;
const ThongTinThanhVien_1 = __importDefault(require("../../model/ThongTinThanhVien"));
const listUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const users = yield ThongTinThanhVien_1.default.findAll({
            raw: true
        });
        res.status(200).json(users);
    }
    catch (error) {
        res.status(500).json({ message: 'Lỗi ' + error.message });
    }
});
exports.listUser = listUser;
const detailUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const maSinhVien = req.query.maSinhVien;
    try {
        const user = yield ThongTinThanhVien_1.default.findOne({
            where: {
                maSinhVien: maSinhVien
            },
            raw: true
        });
        res.status(200).json(user);
    }
    catch (error) {
        res.status(500).json({ message: 'Lỗi ' + error.message });
    }
});
exports.detailUser = detailUser;
const addUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const data = req.body;
    console.log(data);
    try {
        const user = yield ThongTinThanhVien_1.default.create(req.body);
        res.status(201).json({ message: "Tạo mới thành công" });
    }
    catch (error) {
        res.status(400).json({ message: 'Lỗi ' + error.message });
    }
});
exports.addUser = addUser;
const editUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const data = req.body;
    try {
        const user = yield ThongTinThanhVien_1.default.update(data, {
            where: {
                maSinhVien: data.maSinhVien
            }
        });
        if (user[0] == 1) {
            res.status(200).json({ message: "Cập nhật thành công" });
        }
        else {
            res.status(404).json({ message: "Không tìm thấy thành viên" });
        }
    }
    catch (error) {
        res.status(500).json({ message: 'Lỗi ' + error.message });
    }
});
exports.editUser = editUser;
const deleteUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const maSinhVien = req.query.maSinhVien;
    try {
        const user = yield ThongTinThanhVien_1.default.destroy({
            where: {
                maSinhVien: maSinhVien
            }
        });
        if (user == 1) {
            res.status(200).json({ message: "Xóa thành công" });
        }
        else {
            res.status(404).json({ message: "Không tìm thấy thành viên" });
        }
    }
    catch (error) {
        res.status(500).json({ message: 'Lỗi ' + error.message });
    }
});
exports.deleteUser = deleteUser;
