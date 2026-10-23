<p align="center">
  <img src="docs/banner.png" alt="MtFOD Mod" width="100%">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-26.3-3fb950?style=for-the-badge" alt="Minecraft">
  <img src="https://img.shields.io/badge/Fabric-0.19.5-db9b45?style=for-the-badge" alt="Fabric">
  <img src="https://img.shields.io/badge/NeoForge-26.3.0.95-db9b45?style=for-the-badge" alt="NeoForge">
  <img src="https://img.shields.io/badge/Version-0.1.0--beta-c62828?style=for-the-badge" alt="Version">
  <img src="https://img.shields.io/badge/Java-25-e76f00?style=for-the-badge" alt="Java">
  <img src="https://img.shields.io/badge/License-CC0--1.0-lightgrey?style=for-the-badge" alt="License">
  <a href="https://github.com/RainSnowDream/MtF-OD-Mod/releases"><img src="https://img.shields.io/github/v/release/RainSnowDream/MtF-OD-Mod?style=for-the-badge&label=Release&color=c62828" alt="Release"></a>
</p>

<h2 align="center">警示药物滥用与过量（OD）危害的 Minecraft Fabric / NeoForge 模组</h2>

<p align="center"><i>珍爱生命，远离毒品。内容仅供公益 / 教育用途，不构成用药指导。</i></p>

---

## 📥 下载

前往 **[Releases](https://github.com/RainSnowDream/MtF-OD-Mod/releases)** 下载对应加载器的 `.jar`：

| 加载器 | 文件 |
| --- | --- |
| Fabric | `mtfodmod-<版本>+fabric+26.3.jar` |
| NeoForge | `mtfodmod-<版本>+neoforge+26.3.jar` |

## 📖 简介

**MtFOD Mod** 按真实药理学模拟国内被滥用的处方药的作用与危害，让玩家直观感受药物滥用的后果，尤其是**过量（OD，Overdose）的致命性**。

> ⚠️ **免责声明**：本模组仅用于公益 / 教育目的，模拟药物滥用的危害。它不提供任何用药建议，不应被用于获取真实药物信息。如遇药物滥用问题，请寻求专业医疗帮助。

## 💊 物品

| 图标 | 物品 | 真实药理作用（食用后模拟） | 类型 |
|:---:|---|---|:---:|
| <img src="src/main/resources/assets/mtfodmod/textures/item/pregabalin_capsule.png" width="40"> | **普瑞巴林胶囊** | 镇静、眩晕、共济失调、后遗乏力 | 滥用警示 |
| <img src="src/main/resources/assets/mtfodmod/textures/item/dextromethorphan.png" width="40"> | **右美沙芬** | 解离、幻觉、心率加快、血清素综合征 | 滥用警示 |
| <img src="src/main/resources/assets/mtfodmod/textures/item/amantadine.png" width="40"> | **盐酸金刚烷胺** | 强兴奋、幻觉、精神病样症状、心脏毒性 | 滥用警示 |
| <img src="src/main/resources/assets/mtfodmod/textures/item/gabapentin_capsule.png" width="40"> | **加巴喷丁胶囊** | 镇静、眩晕、共济失调、后遗乏力 | 滥用警示 |
| <img src="src/main/resources/assets/mtfodmod/textures/item/yumei_tablet.png" width="40"> | **愈美片**（右美沙芬+愈创木酚甘油醚） | 解离、幻觉、恶心、毒性 | 滥用警示 |
| <img src="src/main/resources/assets/mtfodmod/textures/item/estradiol_valerate.png" width="40"> | **戊酸雌二醇片** | 激素替代治疗（HRT） | 正面 |
| <img src="src/main/resources/assets/mtfodmod/textures/item/androcur_50mg.png" width="40"> | **色谱龙 50mg**（醋酸环丙孕酮） | 抗雄激素（HRT） | 正面 |

## ⚙️ 机制

- **药效分期**：用不同持续时间的效果叠加，近似「初始兴奋 → 高峰幻觉 / 共济失调 → 后遗乏力」。
- **视觉幻觉**：致幻类药品（右美沙芬 / 愈美片 / 金刚烷胺）附带「黑暗脉冲」。
- **幻听**：致幻类药品激活时，随机播放原版恐怖洞穴环境音（`ambient.cave`）。
- **获取**：
  - 创造模式「食物与饮品」标签页；
  - 生存模式：**牧师（Cleric）** 会出售全部药品（药房交易）。
- **清除**：喝**牛奶**可清除所有状态效果。
- **成就**：「第一次 OD」——首次服用滥用药时触发。

## 📥 安装

### Fabric

1. 安装 **Fabric Loader 0.19.5**（针对 Minecraft **26.3**）。
2. 将 [Fabric API](https://modrinth.com/mod/fabric-api) 放入 `mods/`。
3. 下载本模组的 Fabric `.jar` 放入 `mods/`。
4. 启动游戏。

### NeoForge

1. 安装 **NeoForge 26.3.0.95**（针对 Minecraft **26.3**）。
2. 下载本模组的 NeoForge `.jar` 放入 `mods/`。
3. 启动游戏。

## 🔧 构建

需要 **JDK 25**。

```bash
# Fabric（根目录）
./gradlew build          # macOS / Linux
gradlew.bat build        # Windows

# NeoForge（neoforge/ 目录）
cd neoforge
./gradlew build
```

产物：
- Fabric：`build/libs/mtfodmod-0.1.0-beta+fabric+26.3.jar`
- NeoForge：`neoforge/build/libs/mtfodmod-0.1.0-beta+neoforge+26.3.jar`

## 🧩 环境

| 项目 | Fabric | NeoForge |
| --- | --- | --- |
| 模组版本 | `0.1.0-beta+fabric+26.3` | `0.1.0-beta+neoforge+26.3` |
| Minecraft | 26.3 | 26.3 |
| 加载器 | Fabric Loader 0.19.5 | NeoForge 26.3.0.95 |
| 依赖 | Fabric API 0.163.0+26.3 | — |
| Java | 25 | 25 |

## 🤝 参与

欢迎提交 [Issue](https://github.com/RainSnowDream/MtF-OD-Mod/issues/new/choose) 或 PR，详见 [贡献指南](CONTRIBUTING.md)。

## 📜 许可

本项目基于 **CC0-1.0** 发布。
