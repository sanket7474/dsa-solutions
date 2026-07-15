#!/usr/bin/env node
/**
 * Syncs your DSA tracker's data.json into this repo:
 *   1. Creates a Java file stub (with header comment) for every problem
 *      marked "solved" that doesn't already have a file.
 *   2. Regenerates README.md with a table of all solved problems.
 *
 * Usage:
 *   node scripts/generate-readme.js [path-to-tracker-data.json]
 *
 * Default path: ./tracker-data.json (copy it from your tracker app's
 * data/data.json, or use its "Export backup" button and rename the
 * downloaded file to tracker-data.json in this repo's root).
 *
 * Safe to re-run any time — never overwrites a solution file that
 * already exists, only fills in ones that are missing.
 */
const fs = require('fs');
const path = require('path');

const REPO_ROOT = path.join(__dirname, '..');
const dataPath = process.argv[2] || path.join(REPO_ROOT, 'tracker-data.json');

// Maps tracker topic ids -> folder names in this repo.
const TOPIC_FOLDERS = {
  setup: 'setup',
  arrays: 'arrays',
  twoptr: 'two-pointers-sliding-window',
  binsearch: 'binary-search',
  strings: 'strings',
  linkedlist: 'linked-list',
  stacks: 'stacks-queues',
  recursion: 'recursion-backtracking',
  trees: 'trees-bst',
  heaps: 'heaps',
  graphs: 'graphs',
  dp: 'dp',
  greedy: 'greedy-intervals',
  'trie-bits': 'trie-bits',
  revision: 'revision',
};

const TOPIC_TITLES = {
  setup: 'Setup & Recursion Refresh',
  arrays: 'Arrays & Hashing',
  twoptr: 'Two Pointers & Sliding Window',
  binsearch: 'Binary Search',
  strings: 'Strings',
  linkedlist: 'Linked List',
  stacks: 'Stacks & Queues',
  recursion: 'Recursion & Backtracking',
  trees: 'Trees & BST',
  heaps: 'Heaps / Priority Queue',
  graphs: 'Graphs',
  dp: 'Dynamic Programming',
  greedy: 'Greedy & Intervals',
  'trie-bits': 'Tries & Bit Manipulation',
  revision: 'Revision, Contests & Mocks',
};

const NUMBER_WORDS = ['Zero','One','Two','Three','Four','Five','Six','Seven','Eight','Nine'];

function toPascalFilename(name) {
  let cleaned = name.replace(/[()]/g, '').replace(/[^a-zA-Z0-9\s-]/g, '');
  let words = cleaned.split(/[\s-]+/).filter(Boolean);
  words = words.map(w => {
    const digitMatch = w.match(/^(\d+)(.*)$/);
    if (digitMatch) {
      // spell out any leading digits, e.g. "3Sum" -> "ThreeSum"
      const digits = digitMatch[1].split('').map(d => NUMBER_WORDS[+d] || d).join('');
      const rest = digitMatch[2];
      return digits + (rest ? rest.charAt(0).toUpperCase() + rest.slice(1) : '');
    }
    return w.charAt(0).toUpperCase() + w.slice(1);
  });
  return words.join('');
}

function loadData() {
  if (!fs.existsSync(dataPath)) {
    console.error(`\nNo tracker data found at: ${dataPath}`);
    console.error(`Copy your tracker's data/data.json here (or an exported backup)`);
    console.error(`and name it tracker-data.json, or pass a path:\n`);
    console.error(`  node scripts/generate-readme.js /path/to/data.json\n`);
    process.exit(1);
  }
  const raw = fs.readFileSync(dataPath, 'utf-8');
  const parsed = JSON.parse(raw);
  return parsed.phases || parsed;
}

function stubContent(item) {
  return `// ${item.name}
// ${item.link || '(no link on file)'}
// Pattern: ${item.pattern || 'TBD'} | Difficulty: ${item.difficulty || 'TBD'} | Time: TODO | Space: TODO

class Solution {
    // TODO: solve it
}
`;
}

function main() {
  const phases = loadData();
  let created = 0, skipped = 0, totalSolved = 0;
  const readmeSections = [];

  Object.keys(TOPIC_FOLDERS).forEach(topicId => {
    const folder = TOPIC_FOLDERS[topicId];
    const items = (phases[topicId] || []).filter(i => i.status === 'solved');
    if (items.length === 0) return;

    totalSolved += items.length;
    const folderPath = path.join(REPO_ROOT, folder);
    fs.mkdirSync(folderPath, { recursive: true });

    const rows = items.map(item => {
      const filename = toPascalFilename(item.name) + '.java';
      const filePath = path.join(folderPath, filename);
      const relPath = `${folder}/${filename}`;

      if (!fs.existsSync(filePath)) {
        fs.writeFileSync(filePath, stubContent(item), 'utf-8');
        created++;
        console.log(`  + created stub: ${relPath}`);
      } else {
        skipped++;
      }

      const linkCell = item.link ? `[LeetCode](${item.link})` : '—';
      return `| [${item.name}](${relPath}) | ${item.difficulty || '—'} | ${item.pattern || '—'} | ${linkCell} |`;
    });

    readmeSections.push(
      `### ${TOPIC_TITLES[topicId] || topicId} (${items.length})\n\n` +
      `| Problem | Difficulty | Pattern | Source |\n` +
      `|---|---|---|---|\n` +
      rows.join('\n') + '\n'
    );
  });

  const readme = `# DSA Solutions

My solutions as I work through the roadmap in my [DSA tracker](../dsa-tracker-app) — one file per problem, organized by topic, synced automatically from the tracker's data.

**${totalSolved} solved so far.**

Run \`node scripts/generate-readme.js\` any time after updating the tracker to regenerate this table and create stub files for newly-solved problems.

---

${readmeSections.join('\n')}
`;

  fs.writeFileSync(path.join(REPO_ROOT, 'README.md'), readme, 'utf-8');

  console.log(`\nDone — ${created} new stub file(s) created, ${skipped} already existed.`);
  console.log(`README.md regenerated with ${totalSolved} solved problem(s).\n`);
}

main();
