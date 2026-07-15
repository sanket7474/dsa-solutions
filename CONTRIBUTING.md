# Conventions

## Folder structure

One folder per topic, matching the tracker exactly:

| Tracker topic | Folder |
|---|---|
| Setup & Recursion Refresh | `recursion-and-basics/` |
| Arrays & Hashing | `arrays/` |
| Two Pointers & Sliding Window | `two-pointers-sliding-window/` |
| Binary Search | `binary-search/` |
| Strings | `strings/` |
| Linked List | `linked-list/` |
| Stacks & Queues | `stacks-queues/` |
| Recursion & Backtracking | `recursion-backtracking/` |
| Trees & BST | `trees-bst/` |
| Heaps / Priority Queue | `heaps/` |
| Graphs | `graphs/` |
| Dynamic Programming | `dp/` |
| Greedy & Intervals | `greedy-intervals/` |
| Tries & Bit Manipulation | `trie-bits/` |
| Revision, Contests & Mocks | `revision/` |

## File naming

`PascalCase` version of the problem's exact title, `.java` extension.

- "Two Sum" → `TwoSum.java`
- "Longest Substring Without Repeating Characters" → `LongestSubstringWithoutRepeatingCharacters.java`
- "3Sum" → `ThreeSum.java` (leading digits get spelled out)

The generator script does this conversion automatically for stub files —
if you create a file by hand, match the same convention so the sync
doesn't create a duplicate stub next to it.

## File header

Every solution file starts with a 3-line header:

```java
// Problem Name
// https://leetcode.com/problems/problem-slug/
// Pattern: PatternName | Difficulty: Easy/Medium/Hard | Time: O(n) | Space: O(1)

class Solution {
    // ...
}
```

Fill in Time/Space after you solve it — that's the part worth writing
down yourself, since it's the part interviewers actually probe on.

## Commits

One commit per problem, not batched — your commit history becomes a
second, independent record of your daily consistency, separate from
the tracker's heatmap.

```
arrays: solve Two Sum (Easy, Hashing)
dp: solve Coin Change (Medium, 1D DP) — needed hint 2
```

The optional `— needed hint N` suffix is a fast way to search your own
history later:

```
git log --all --grep="needed hint"
```

## Keeping this repo in sync with the tracker

1. In the tracker app, click **Export backup** (top controls bar).
2. Copy the downloaded file into this repo's root, renamed to
   `tracker-data.json` (or pass its path directly to the script).
3. Run:
   ```
   npm run generate
   ```
   This creates a stub `.java` file (header pre-filled) for any newly
   solved problem that doesn't have a file yet, and regenerates
   `README.md` with an up-to-date table. It never touches or overwrites
   a solution file that already exists.
4. Fill in the actual solution code in any freshly created stub, commit.

Safe to run as often as you like — re-running after no changes just
regenerates the same README.
