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

<h2 align="center">A Minecraft Fabric / NeoForge mod that warns about drug abuse and overdose (OD)</h2>

<p align="center"><i>Cherish life, stay away from drugs. For public education only; not medical advice.</i></p>

<p align="center"><a href="README.md" target="_blank">中文</a> | <a href="README.en.md" target="_blank">English</a></p>

---

## 📥 Download

Grab the `.jar` for your loader from **[Releases](https://github.com/RainSnowDream/MtF-OD-Mod/releases)**:

| Loader | File |
| --- | --- |
| Fabric | `mtfodmod-<version>+fabric+26.3.jar` |
| NeoForge | `mtfodmod-<version>+neoforge+26.3.jar` |

## 📖 About

**MtFOD Mod** simulates the real pharmacological effects and harms of prescription drugs commonly abused in China, so players can feel the consequences of drug abuse — especially the lethality of **overdose (OD)**.

> ⚠️ **Disclaimer**: This mod is for public-education purposes only. It does not provide any dosing advice and should not be used to obtain real drug information. If you have a substance-use problem, please seek professional help.

## 💊 Items

| Icon | Item | Simulated effects | Type |
|:---:|---|---|:---:|
| <img src="src/main/resources/assets/mtfodmod/textures/item/pregabalin_capsule.png" width="40"> | **Pregabalin Capsule** | Sedation, dizziness, ataxia, hangover | Abuse warning |
| <img src="src/main/resources/assets/mtfodmod/textures/item/dextromethorphan.png" width="40"> | **Dextromethorphan** | Dissociation, hallucinations, tachycardia, serotonin syndrome | Abuse warning |
| <img src="src/main/resources/assets/mtfodmod/textures/item/amantadine.png" width="40"> | **Amantadine HCl** | Strong stimulation, hallucinations, psychosis-like symptoms, cardiotoxicity | Abuse warning |
| <img src="src/main/resources/assets/mtfodmod/textures/item/gabapentin_capsule.png" width="40"> | **Gabapentin Capsule** | Sedation, dizziness, ataxia, hangover | Abuse warning |
| <img src="src/main/resources/assets/mtfodmod/textures/item/yumei_tablet.png" width="40"> | **Yumei Tablet** (DXM + guaifenesin) | Dissociation, hallucinations, nausea, toxicity | Abuse warning |
| <img src="src/main/resources/assets/mtfodmod/textures/item/estradiol_valerate.png" width="40"> | **Estradiol Valerate** | Hormone replacement therapy (HRT) | Positive |
| <img src="src/main/resources/assets/mtfodmod/textures/item/androcur_50mg.png" width="40"> | **Androcur 50mg** (cyproterone acetate) | Anti-androgen (HRT) | Positive |

## ⚙️ Mechanics

- **Phased effects**: stacked effects with different durations approximate "initial rush → peak dissociation / ataxia → hangover".
- **Visual hallucination**: hallucinogenic items (dextromethorphan / yumei / amantadine) apply "darkness pulses".
- **Auditory hallucination**: while hallucinating, the vanilla creepy cave ambience (`ambient.cave`) plays at random positions.
- **Obtaining**:
  - Creative "Food & Drinks" tab;
  - Survival: the **Cleric** sells all items (pharmacy trades).
- **Clearing**: drink **milk** to clear all status effects.
- **Advancement**: "First OD" — triggered on first use of an abused drug.

## 📥 Installation

### Fabric

1. Install **Fabric Loader 0.19.5** (Minecraft **26.3**).
2. Put [Fabric API](https://modrinth.com/mod/fabric-api) in `mods/`.
3. Put the Fabric `.jar` in `mods/`.
4. Launch the game.

### NeoForge

1. Install **NeoForge 26.3.0.95** (Minecraft **26.3**).
2. Put the NeoForge `.jar` in `mods/`.
3. Launch the game.

## 🔧 Building

Requires **JDK 25**.

```bash
# Fabric (repo root)
./gradlew build          # macOS / Linux
gradlew.bat build        # Windows

# NeoForge (neoforge/)
cd neoforge
./gradlew build
```

Outputs:
- Fabric: `build/libs/mtfodmod-0.1.0-beta+fabric+26.3.jar`
- NeoForge: `neoforge/build/libs/mtfodmod-0.1.0-beta+neoforge+26.3.jar`

## 🧩 Environment

| Item | Fabric | NeoForge |
| --- | --- | --- |
| Mod version | `0.1.0-beta+fabric+26.3` | `0.1.0-beta+neoforge+26.3` |
| Minecraft | 26.3 | 26.3 |
| Loader | Fabric Loader 0.19.5 | NeoForge 26.3.0.95 |
| Dependency | Fabric API 0.163.0+26.3 | — |
| Java | 25 | 25 |

## 🤝 Contributing

Feel free to open an [Issue](https://github.com/RainSnowDream/MtF-OD-Mod/issues/new/choose) or a PR. See [CONTRIBUTING.md](CONTRIBUTING.md).

## 📜 License

Released under **CC0-1.0**.
