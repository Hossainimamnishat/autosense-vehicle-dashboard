# 🗺️ Charging Station Finder - User Flow Guide

## Visual Flow Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    BMW DASHBOARD                             │
│  ┌──────────────────────────────────────────────────────┐   │
│  │                Quick Controls                         │   │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐              │   │
│  │  │ Climate │  │Live Map │  │  Light  │              │   │
│  │  └─────────┘  └─────────┘  └─────────┘              │   │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐              │   │
│  │  │🔋CHARGING│  │  Music  │  │Direction│  ←── CLICK  │   │
│  │  └─────────┘  └─────────┘  └─────────┘              │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                            ↓
                    User clicks "Charging"
                            ↓
┌─────────────────────────────────────────────────────────────┐
│          CHARGING STATION FINDER SCREEN                      │
├─────────────────────────────────────────────────────────────┤
│  ← Back    Charging Stations    ⚡                          │
│            6 stations nearby                                 │
├──────────────────────┬──────────────────────────────────────┤
│   STATION LIST       │        GOOGLE MAPS                   │
│ ┌──────────────────┐ │  ┌────────────────────────────────┐ │
│ │ Tesla SC         │ │  │  📍 Your Location             │ │
│ │ Arabellastraße   │ │  │                                │ │
│ │ 1.8 km  ⭐4.8   │ │  │    🟢 Stations                │ │
│ │ 8/12 | Rapid     │ │  │                                │ │
│ └──────────────────┘ │  │         Route Line 📍➜🟢      │ │
│ ┌──────────────────┐ │  │                                │ │
│ │ BMW Welt Hub     │ │  │                                │ │
│ │ Am Olympiapark 1 │◄┼──┼── SELECTED (Highlighted)       │ │
│ │ 2.3 km  ⭐4.9   │ │  │                                │ │
│ │ 6/8 | Rapid      │ │  │                                │ │
│ └──────────────────┘ │  └────────────────────────────────┘ │
│ ┌──────────────────┐ │  ┌────────────────────────────────┐ │
│ │ EnBW Hypercharger│ │  │ Selected: BMW Welt Hub         │ │
│ │ Schwabing        │ │  │ 6 chargers available • 2.3 km  │ │
│ │ 3.1 km  ⭐4.7   │ │  │          [🧭 Navigate]         │ │
│ │ 4/6 | Rapid      │ │  └────────────────────────────────┘ │
│ └──────────────────┘ │                                      │
│        ⋮             │                                      │
└──────────────────────┴──────────────────────────────────────┘
                            ↓
              User clicks "Navigate" button
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                    GOOGLE MAPS APP                           │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Turn-by-turn Navigation to:                         │   │
│  │  BMW Welt Charging Hub                               │   │
│  │  Am Olympiapark 1, Munich                            │   │
│  │                                                       │   │
│  │  📍 ━━━━━━━━━━━━━━━━━━━━━━━━━━━ 🎯                │   │
│  │                                                       │   │
│  │  Distance: 2.3 km                                    │   │
│  │  ETA: 8 minutes                                      │   │
│  │                                                       │   │
│  │  Turn left in 200m...                                │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

---

## 🎬 Step-by-Step User Journey

### Step 1: Dashboard View
```
User sees the BMW dashboard with vehicle stats, map, media player
          ↓
Notices battery at 92% but wants to plan ahead
          ↓
Looks at Quick Controls section
```

### Step 2: Access Charging Finder
```
Clicks "Charging" button (battery icon)
          ↓
Screen smoothly transitions to Charging Station Finder
          ↓
Top bar shows: "← Back | Charging Stations | 6 stations nearby | ⚡"
```

### Step 3: Browse Stations
```
Left side shows scrollable list of 6 nearby stations
          ↓
Each station card displays:
  • Station name (e.g., "BMW Welt Charging Hub")
  • Address (e.g., "Am Olympiapark 1, Munich")
  • Distance badge (e.g., "2.3 km")
  • Charger availability (e.g., "6/8" with green icon)
  • Charging speed (e.g., "Rapid" with yellow icon)
  • Price (e.g., "€0.45/kWh" with blue icon)
  • Rating (e.g., "4.9⭐" with yellow star)
  • Status (e.g., "● Open Now" in green)
```

### Step 4: View Map
```
Right side shows Google Maps with:
  • Blue marker: Your current location
  • Green markers: All 6 charging stations
  • Map can be zoomed and panned
```

### Step 5: Select a Station
```
User clicks on "BMW Welt Charging Hub" card
          ↓
Card highlights with elevated shadow
          ↓
Map automatically:
  • Zooms to the selected station
  • Changes marker to cyan color
  • Draws blue route line from current location
          ↓
Bottom of map shows selected station details:
  "BMW Welt Charging Hub"
  "6 chargers available • 2.3 km away"
  [🧭 Navigate] button appears
```

### Step 6: Navigate
```
User clicks "Navigate" button
          ↓
App attempts to open Google Maps app
          ↓
If Google Maps installed:
  ✓ Opens with navigation intent
  ✓ Shows turn-by-turn directions
  ✓ ETA and distance displayed
          ↓
If not installed:
  ✓ Opens Google Maps in web browser
  ✓ Shows directions on web interface
```

### Step 7: Arrival
```
User follows Google Maps directions
          ↓
Arrives at BMW Welt Charging Hub
          ↓
Finds 6 available chargers
          ↓
Plugs in and charges
          ↓
Can return to dashboard by clicking Back button
```

---

## 🎨 UI Element Details

### Station Card Components
```
┌─────────────────────────────────────────────┐
│ BMW Welt Charging Hub           [2.3 km]   │ ← Name & Distance
│ Am Olympiapark 1, Munich                    │ ← Address
│                                             │
│ 🚗6/8  ⚡Rapid  💰€0.45/kWh  ⭐4.9         │ ← Quick Info
│                                             │
│ ● Open Now                                  │ ← Status
└─────────────────────────────────────────────┘
```

### Map Markers
```
📍 = Your Location (Blue)
🟢 = Charging Station (Green)
🔵 = Selected Station (Cyan)
━━ = Route Line (Blue, 8px width)
```

### Color Coding
```
Available Chargers:
  🟢 Green = Available (> 0)
  🟠 Orange = None available (0)

Status:
  ● Green = Open
  ● Red = Closed

Charging Speed:
  ⚡ Yellow = Fast/Rapid
  🔋 Blue = Normal/Slow
```

---

## 📐 Layout Specifications

### Screen Dimensions
- **Resolution:** 1408×768 (Landscape)
- **Top Bar:** 70dp height
- **Content Area:** Flexible (fills remaining space)
- **Left Panel:** 33% width
- **Right Panel:** 67% width

### Card Sizes
- **Station Card:** Full width × 140dp height
- **Card Spacing:** 12dp vertical gap
- **Card Radius:** 16dp rounded corners
- **Elevation:** 2dp (normal), 8dp (selected)

### Map Elements
- **Marker Size:** Standard Google Maps size
- **Route Line:** 8px width, BMW Blue color
- **Camera Zoom:** Level 13 (overview), Level 15 (selected)

---

## 🔄 Interaction States

### Station Card States
```
1. Normal:
   Background: BMWCardBackground
   Elevation: 2dp
   Border: None

2. Selected:
   Background: BMWCardElevated (lighter)
   Elevation: 8dp
   Border: None (highlighted by elevation)

3. Pressed:
   Ripple effect (Material 3)
   Slight scale animation
```

### Navigate Button States
```
1. Default:
   Background: BMWBlueAccent
   Text: White
   Size: Height 50dp

2. Pressed:
   Background: Darker blue
   Ripple effect

3. Disabled (no station selected):
   Not shown (only appears when station selected)
```

---

## 🎯 Key User Benefits

✅ **Find nearest charging station** in seconds
✅ **See real-time availability** before driving
✅ **Compare prices** across stations
✅ **Check ratings** for best experience
✅ **Navigate directly** without switching apps
✅ **Plan ahead** with distance information
✅ **Save time** by seeing open/closed status

---

## 🔊 User Feedback Elements

### Visual Feedback
- Card elevation changes on selection
- Map zooms smoothly to selected station
- Route line animates drawing
- Button ripple effects

### Information Feedback
- Station count in top bar
- Distance badges on cards
- Charger availability icons
- Open/closed status indicators

### Navigation Feedback
- Back button returns to dashboard
- Selected station details shown
- Navigate button clearly visible
- Map updates in real-time

---

## 💡 Pro Tips for Users

1. **Select before navigating** - Always select a station to see route first
2. **Check availability** - Look for green icons (chargers available)
3. **Compare prices** - Stations range from €0.39 to €0.69 per kWh
4. **Read ratings** - Higher rated stations = better experience
5. **Verify hours** - Check "Open Now" status before going
6. **Plan distance** - Consider stations within 2-3 km for efficiency

---

## 🚗 Real-World Usage Scenarios

### Scenario 1: Daily Commute
```
User: Regular commuter in Munich
Need: Quick charge during work hours
Action: Opens charging finder → Selects BMW Welt Hub (closest to office)
Result: Charges during lunch break
```

### Scenario 2: Low Battery Alert
```
User: Battery at 15%
Need: Immediate fast charging
Action: Filters by "Rapid" → Chooses Tesla Supercharger (8/12 available)
Result: Fast charge in 30 minutes
```

### Scenario 3: Weekend Planning
```
User: Planning long drive
Need: Check all options near route
Action: Views all stations on map → Selects highest rated
Result: Best charging experience
```

---

**End of User Flow Guide**

*For technical documentation, see `CHARGING_STATION_FEATURE.md`*
*For implementation details, see `IMPLEMENTATION_COMPLETE.md`*

