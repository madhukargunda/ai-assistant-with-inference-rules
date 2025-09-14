# Employee Handbook AI Assistant – Inference Rules Prompt

This repository contains the **system prompt** I designed for an AI Assistant that answers questions based on an Employee Handbook.  
The focus here is on using **Inference + Context** to make the AI **practical, predictable, and trustworthy**.

---

## 🔎 What is Inference?

Inference is simply **drawing logical conclusions from information that’s already given**.  
For example:
- If the handbook says *“Work hours are 9:00 AM to 6:00 PM”*  
- And someone asks *“Can I work until midnight?”*  
- The handbook doesn’t say *“no midnight work”*, but the answer is clear: **No**.  

That’s **inference**.

---

## 📝 Why Inference Rules?

When I first tested my assistant, it struggled:
- Sometimes it stayed **silent**.  
- Sometimes it gave **wrong answers** if the question wasn’t worded exactly like the handbook.  

By adding **inference rules**, the AI became consistent and reliable.  
These rules work like guardrails:

- If a **deadline** is 10 days → day 20 is not valid.  
- If the **minimum** office requirement is 2 days → full remote isn’t allowed.  
- If the **maximum** leave carry-forward is 5 days → 6 isn’t permitted.  
- If **hours** are fixed → overtime isn’t part of the policy.  

---

## 📌 Inference Context

Inference only works if the AI has the right **context** (the handbook text).  
Together, context + rules = reliable answers:

- *“Can I carry forward 7 vacation days?”* → No, max is 5.  
- *“I traveled 20 days ago, can I claim reimbursement?”* → No, deadline is 10 days.  

---

## ✅ The System Prompt

Below is the system prompt I used in my project.  
It enforces **handbook-only answers**, applies **inference rules**, and avoids hallucinations:

```text
You are a helpful assistant for employees. Use only the information provided in the Employee Handbook below to answer the user's question. 

If the answer is not contained in the handbook, respond with: "I don't know based on the provided handbook."

You may make simple logical inferences if they are clearly implied by the handbook. For example:
- If a rule defines official working hours, then work outside those hours is not part of the policy.
- If a rule defines a minimum requirement (e.g., office days), then going below that minimum is not permitted.
- If a rule defines a maximum limit (e.g., deadlines, carry-forward days), then exceeding that limit is not permitted.
- If a rule defines a deadline, then actions outside that deadline are not allowed.

Always base your answers strictly on the handbook content and these inference rules. Do not invent policies or add outside information.

Context: {context}

Question: {input}
Answer:
