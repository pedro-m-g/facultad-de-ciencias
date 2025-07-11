<?php

namespace App\Http\Controllers;

use App\Http\Requests\StoreCoachRequest;
use App\Models\Coach;
use Illuminate\Support\Facades\Redirect;
use Inertia\Inertia;

class CoachController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $this->authorize('viewAny', Coach::class);

        $coaches = Coach::paginate();
        return Inertia::render('Coaches/Index', [
            'coaches' => $coaches
        ]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return Inertia::render('Coaches/Create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreCoachRequest $request)
    {
        $this->authorize('create', Coach::class);
        $coachData = $request->validated();

        try {
            Coach::create($coachData);
            return Redirect::route('coaches.index')->with('success', 'Coach created successfully.');
        } catch (\Exception $e) {
            return Redirect::back()->withErrors(['error' => 'Failed to create coach.']);
        }

        return Redirect::route('coaches.index');
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        $coach = Coach::findOrFail($id);
        return Inertia::render('Coaches/Show', [
            'coach' => $coach
        ]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        $coach = Coach::findOrFail($id);
        return Inertia::render('Coaches/Edit', [
            'coach' => $coach
        ]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(StoreCoachRequest $request, string $id)
    {
        $coach = Coach::findOrFail($id);
        $this->authorize('update', $coach);
        $coachData = $request->validated();

        try {
            $coach->update($coachData);
            return Redirect::route('coaches.index')->with('success', 'Coach updated successfully.');
        } catch (\Exception $e) {
            return Redirect::back()->withErrors(['error' => 'Failed to update coach.']);
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $coach = Coach::findOrFail($id);
        $this->authorize('delete', $coach);

        try {
            $coach->delete();
        } catch (\Exception $e) {
            return Redirect::back()->withErrors(['error' => 'Failed to delete coach.']);
        }
    }
}
