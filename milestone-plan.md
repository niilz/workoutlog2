Here's the milestone plan, written so a coding agent can execute it directly in Figma.

---

# Workout Tracker — Figma Design System Milestone Plan

**Design Direction:** Clean, structured layouts with strong color highlights and high contrast accents. Light base with bold primary color pops. Sharp typography, generous whitespace, purposeful use of color only where it matters (CTAs, state indicators, active elements).
**Platform Scope:** Shared component language targeting both Android (Material You conventions) and iOS (HIG conventions), with platform-specific variants where needed.

---

## Milestone 1 — Design Foundations & Tokens

Establish the visual DNA of the app. All subsequent milestones depend on this being complete and consistent.

**Deliverables:**

- **Color palette** — Define a neutral base (near-white background, dark text), one strong primary accent (e.g. electric blue, lime, or coral), one secondary accent, semantic colors for success (completed), warning (skipped), destructive (delete), and disabled states. Create Figma color variables for each.
- **Typography scale** — Choose two typefaces: one strong display/header font, one clean body font. Define a scale: Display, H1, H2, H3, Body, Caption, Label, Overline. Map each to font size, weight, line height, and letter spacing as Figma text styles.
- **Spacing & grid system** — 8pt base grid. Define spacing tokens: 4, 8, 12, 16, 24, 32, 48, 64. Define a 4-column grid for mobile (360px / 390px frames) for both Android and iOS.
- **Elevation & shadow tokens** — Three levels: subtle card shadow, modal overlay shadow, floating action button shadow.
- **Icon style definition** — Specify stroke width, corner radius, and size (24px default). Identify all icons needed: play, pause, plus, trash, check, skip, chevron, back, edit, filter, calendar, bar chart, timer.
- **Border radius tokens** — Define a consistent radius scale: small (4px), medium (8px), large (16px), pill (999px).

---

## Milestone 2 — Core UI Components

Build the atomic and molecular components that recur throughout the entire app.

**Deliverables:**

- **Buttons** — Primary (filled, accent color), Secondary (outlined), Destructive, Icon Button, FAB (floating action button with plus icon). All with default, hover/pressed, disabled states.
- **Header / App Bar** — Left: back or menu icon. Center: screen title. Right: user name display (avatar or initials + name label). Both Android and iOS variants.
- **Timer display component** — Large monospace time readout (MM:SS or HH:MM:SS). Play/Pause toggle button integrated below or alongside. Idle and active states.
- **List item — Workout** — Title, subtitle (e.g. exercise count), chevron. Tappable, with pressed state.
- **List item — Exercise (collapsed)** — Exercise name, set count summary (e.g. "4 sets · 80 kg"), chevron/expand indicator.
- **List item — Exercise (expanded)** — Shows individual set rows below the exercise header. Each set row: set number, weight input, reps input, check button, skip button.
- **Set row component** — Inline editable fields for weight and reps. State variants: default, active/editing, completed (check filled, accent color), skipped (muted, strikethrough or skip icon highlighted).
- **Input fields** — Numeric input (for weight/reps), text input (for name/description). With label, placeholder, focused, error states.
- **Chips / Tags** — For filter states in statistics (e.g. "By weight", "By frequency").
- **Empty state component** — Illustration placeholder + message + CTA button. Used on landing when no workout is running, on exercise lists, on stats.

---

## Milestone 3 — Screen Designs: Core Workout Flow

Design all screens related to the main workout experience.

**Deliverables:**

- **Landing Screen** — Header with user name (top right). Two primary CTAs: "Select Workout" and "Previous Workouts". Timer area (visible only when a workout is running): large timer display + Play/Pause button. Idle state (no workout running) and Active state (timer visible).
- **Workout Selection Screen** — Scrollable list of available workouts. Each workout is a list item. Plus button (FAB or header icon) to create a new workout. Trash icon per item to delete.
- **Workout Detail / Edit Screen** — Workout name (editable). List of exercises (collapsed by default). Plus to add exercise, trash per exercise. Reorder affordance (drag handle or edit mode).
- **Active Workout Screen** — Workout name + timer in header. Exercise list with expand/collapse. Each expanded exercise shows all sets. Per-set: weight, reps, check/skip. Overall exercise check/skip. Inline editing of sets.
- **Exercise Edit Screen** — Name field, description field, set list. Each set: weight + reps fields. Plus to add set, trash per set.

---

## Milestone 4 — Screen Designs: Statistics & History

Design all screens related to progress tracking and data visualisation.

**Deliverables:**

- **Statistics Hub Screen** — Navigation tabs or segmented control for: Training History, Calendar View, Exercise Charts.
- **Training History List** — Scrollable list of past workouts: date, workout name, duration. Tappable to expand/navigate to detail. Detail view: list of exercises performed with sets, reps, weights.
- **Calendar View** — Month grid. Days with a completed workout are highlighted (accent dot or filled cell). Navigation between months. Tapping a highlighted day shows a summary popover or navigates to that session's detail.
- **Exercise Bar Chart Screen** — Horizontal or vertical bar chart of exercises. Sort controls (chips): "Times performed", "Last performed", "Weight lifted". Tapping a bar selects the exercise and navigates to the exercise detail graph screen.
- **Exercise Detail Graph Screen** — Exercise name as title. Segmented control or tab switcher for metric: Total Volume (reps × weight), Max Weight, Max Reps, Max Set Total. Line graph: X-axis = date of workout, Y-axis = selected metric. Data points tappable to show exact value tooltip. Smooth, clean graph with accent color line and subtle fill.

---

## Milestone 5 — States, Edge Cases & Prototype

Ensure the design system is complete and interactive enough for handoff and agent-driven implementation.

**Deliverables:**

- **All component states documented** — Every component from Milestone 2 must have all states visible in a dedicated component states frame: default, pressed, disabled, loading (where applicable), completed, skipped.
- **Error & validation states** — Empty required fields, invalid numeric input, no internet / no data states.
- **Onboarding / first-run state** — Landing screen when no workouts exist yet. Prompt the user to create their first workout.
- **Figma prototype links** — Connect the key user flows interactively: Landing → Select Workout → Active Workout → Complete. Landing → Statistics → Calendar → Session Detail. Exercise Bar Chart → Exercise Detail Graph.
- **Annotation layer** — Add design notes on spacing, interaction behavior (expand/collapse), and platform-specific notes (Android FAB vs iOS navigation patterns) using Figma's annotation tools or a dedicated notes component.
- **Component naming audit** — Ensure all components follow a consistent naming convention: `[Screen/Group]/[ComponentName]/[Variant]` so the coding agent can map Figma component names directly to KMP composable/SwiftUI view names.

---

## Implementation Notes for the Coding Agent

- Use **Figma Variables** (not just styles) for all color and spacing tokens, so they can be exported and mapped directly to KMP `designTokens` or a `Theme.kt` / Swift equivalent.
- All components should use **auto layout** so they resize predictably when implemented in Compose/SwiftUI.
- Provide **both 390×844 (iOS)** and **360×800 (Android)** frame sizes for every screen.
- Icon components should be exportable as SVG at 24×24.
- The graph components in Milestone 4 are design mockups — the coding agent will implement them using a charting library (e.g. Vico for Android / Swift Charts for iOS); the Figma design defines visual style only, not chart library specifics.
