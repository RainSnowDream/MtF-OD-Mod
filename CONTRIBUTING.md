# 贡献指南

感谢你对 **MtFOD Mod** 的关注！

## 报告问题

请使用 [Issue 模板](https://github.com/RainSnowDream/MtF-OD-Mod/issues/new/choose) 提交，尽量附上：

- Minecraft 版本（如 26.3）、加载器（Fabric / NeoForge）、模组版本
- 复现步骤
- 日志（`latest.log`）或崩溃报告的相关部分

## 提交代码

1. Fork 本仓库并新建分支
2. 修改后确保能构建通过：
   - Fabric（根目录）：`./gradlew build`
   - NeoForge（`neoforge/`）：`cd neoforge && ./gradlew build`
3. 提交 PR 并说明改动内容

## 开发环境

- **JDK 25**
- Fabric：Gradle 9.x + Loom（根目录）
- NeoForge：ModDevGradle（`neoforge/`）
- 首次构建会下载并反编译 Minecraft，较慢；之后有缓存会很快

## 代码风格

- 遵循现有代码格式（见 `.editorconfig`）
- 物品/配方/语言的改动请同时更新中英文文件
